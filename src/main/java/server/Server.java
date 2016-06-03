package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import controller.Controller;
import game.GameState;
import game.Giocatore;
import view.ServerSocketView;

public class Server {

	private final static int PORT = 29999;
	private GameState gameState;
	private Controller controller;
	private List<Giocatore> giocatori;
	private boolean end = false;

	public Server() {
		this.gameState = new GameState();
		this.giocatori = new ArrayList<>();
		this.controller = new Controller(this.gameState);
	}

	private void startSocket() throws IOException {

		ExecutorService executor = Executors.newCachedThreadPool();

		ServerSocket serverSocket = new ServerSocket(PORT);

		System.out.println("SERVER SOCKET READY ON PORT " + PORT);

		while (!end) {
			Socket socket = serverSocket.accept();
			ServerSocketView view = new ServerSocketView(socket, gameState, this);

			this.gameState.registerObserver(view);
			view.registerObserver(this.controller);

			executor.submit(view);
		}

		serverSocket.close();
	}

	public void aggiungiGiocatore(Giocatore giocatore) {
		giocatori.add(giocatore);

		if (giocatori.size() == 2) {
			creaGioco();
		}

	}

	private void creaGioco() {
		try {
			gameState.start(this.giocatori);
			giocatori.clear();
			gameState = new GameState();
			controller = new Controller(gameState);
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