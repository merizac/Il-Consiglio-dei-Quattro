package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import controller.Controller;
import game.GameState;
import game.Giocatore;
import view.ServerRMIView;
import view.ServerRMIViewRemote;
import view.ServerSocketView;
import view.View;

public class Server {

	private final static int CONNESSIONESOCKET = 29999;
	private final static int CONNESSIONERMI = 1099;
	private Controller controller;
	private GameState gameState;
	private Map<GameState, Set<View>> partite;
	private List<Giocatore> giocatori;
	private boolean end = false;
	private final int TIMEOUT = 5000;
	private Timer timer;
	private Registry registry;

	public Server() {
		this.partite = new HashMap<>();
		this.gameState = new GameState();
		this.controller = new Controller(gameState);
		this.partite.put(gameState, new HashSet<>());
		this.giocatori = new ArrayList<>();
	}

	private void startSocket() throws IOException {

		ExecutorService executor = Executors.newCachedThreadPool();

		ServerSocket serverSocket = new ServerSocket(CONNESSIONESOCKET);

		System.out.println("SERVER SOCKET READY ON PORT " + CONNESSIONESOCKET);

		while (!end) {
			Socket socket = serverSocket.accept();
			ServerSocketView view = new ServerSocketView(socket, this);
			executor.submit(view);
		}

		serverSocket.close();
	}

	private void startRMI() throws RemoteException, AlreadyBoundException {
		
		this.registry = LocateRegistry.createRegistry(CONNESSIONERMI);
		
		ServerRMIViewRemote game = new ServerRMIView(this);
		ServerRMIViewRemote gameRemote = (ServerRMIViewRemote) UnicastRemoteObject.exportObject(game, 0);
		
		String name = "GIOCO";
		registry.bind(name, gameRemote);
	}

	public synchronized void aggiungiGiocatore(Giocatore giocatore, View view) {
		this.giocatori.add(giocatore);
		this.partite.get(gameState).add(view);
		if (giocatori.size() == 2) {
			timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					creaGioco();

				}
			}, TIMEOUT);
		}
	}

	public void aggiungiGiocatoreRMI(Giocatore giocatore, ServerRMIView view) throws RemoteException {
		this.aggiungiGiocatore(giocatore, view);
		
		//ServerRMIViewRemote gameRemote = (ServerRMIViewRemote) UnicastRemoteObject.exportObject(view, 0);
		
		String name = "GIOCO";
		registry.rebind(name, view);

	}

	private void creaGioco() {
		try {
			for (View v : this.partite.get(gameState)) {
				v.setGameState(gameState);
				this.gameState.registerObserver(v);
				v.registerObserver(this.controller);
			}
			this.gameState.start(giocatori);
			this.giocatori.clear();
			this.gameState = new GameState();
			this.controller = new Controller(gameState);
			this.partite.put(this.gameState, new HashSet<>());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		try {
			server.startRMI();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.startSocket();
	}

}