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
	// private Timer timer;
	// private final static Time TIMEOUT=new Time(10000);

	public Server() {
		this.gameState = new GameState();
		this.controller = new Controller(gameState);
		this.giocatori=new ArrayList<>();
	}

	private void startSocket() throws IOException {

		ExecutorService executor = Executors.newCachedThreadPool();

		ServerSocket serverSocket = new ServerSocket(PORT);

		System.out.println("SERVER SOCKET READY ON PORT " + PORT);

		while (true) {
			Socket socket = serverSocket.accept();
			ServerSocketView view = new ServerSocketView(socket, gameState, this );

			this.gameState.registerObserver(view);
			view.registerObserver(this.controller);

			executor.submit(view);
		}
	}

	public void aggiungiGiocatore(Giocatore giocatore) {
		giocatori.add(giocatore);
	//	System.out.println(giocatori);
		if (giocatori.size() == 2)
			try {
				gameState.start(giocatori);
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
