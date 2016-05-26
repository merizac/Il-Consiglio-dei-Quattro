package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.rules.Timeout;

import controller.Controller;
import game.GameState;
import game.Giocatore;
import gameDTO.GameStateDTO;
import view.ServerSocketView;

public class Server {

	private final static int PORT=29999;
	private GameState gameState;
	private Controller controller;
	private List<Giocatore> giocatori;
	//private Timer timer;
	//private final static Time TIMEOUT=new Time(10000);
	
	public Server(){
		giocatori=new ArrayList<>();
		try {
			this.gameState=new GameState();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.controller=new Controller(gameState);
	}
	
	private void startSocket() throws IOException{
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		ServerSocket serverSocket=new ServerSocket(PORT);
		
		System.out.println("SERVER SOCKET READY ON PORT "+ PORT);
		
		int numeroGiocatori=0;
		while(numeroGiocatori<2){
			Socket socket=serverSocket.accept();
			numeroGiocatori++;
			ServerSocketView view=new ServerSocketView(socket, gameState, this);
			this.gameState.registerObserver(view);
			view.registerObserver(this.controller);
			executor.submit(view);
		}
		startGame();
		}
	
	public synchronized void aggiungiGiocatore(Giocatore giocatore) {
		giocatori.add(giocatore);
		notify();
	}
	
	private synchronized void startGame(){
		
		while(giocatori.size()!=2){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		gameState.setGiocatori(giocatori);
		gameState.start();
		System.out.println("funziona");
		System.out.println(gameState.getGiocatori());
	
	}
	
	public static void main(String[] args) throws IOException{
		Server server=new Server();
		server.startSocket();
	}

}
