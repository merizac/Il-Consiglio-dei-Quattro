package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import controller.Controller;
import game.GameState;
import game.Giocatore;
import view.ServerSocketView;
import view.View;

public class Server {

	private final static int PORT = 29999;
	private Controller controller;
	private GameState gameState;
	private Map<GameState, Set<View>> partite;
	private List<Giocatore> giocatori;
	private boolean end = false;
	private Object lock=new Object();

	public Server() {
		this.partite = new HashMap<>();
		this.gameState = new GameState();
		this.controller = new Controller(gameState);
		this.partite.put(gameState, new HashSet<>());
		this.giocatori = new ArrayList<>();
	}

	private void startSocket() throws IOException {

		ExecutorService executor = Executors.newCachedThreadPool();

		ServerSocket serverSocket = new ServerSocket(PORT);

		System.out.println("SERVER SOCKET READY ON PORT " + PORT);

		while (!end) {
			Socket socket = serverSocket.accept();
			ServerSocketView view = new ServerSocketView(socket, this);
			executor.submit(view);
		}

		serverSocket.close();
	}

	public void aggiungiGiocatore(Giocatore giocatore, ServerSocketView view) {
		synchronized(lock) {
			this.giocatori.add(giocatore);
			this.partite.get(gameState).add(view);
			if (giocatori.size() == 2)
				creaGioco();
		}

	}

	private void creaGioco() {
		try {
			for (View v : this.partite.get(gameState)) {
				v.setGameState(this.gameState);
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
		server.startSocket();
	}

}