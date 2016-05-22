package it.polimi.ingsw.cg17;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.Controller;
import game.GameState;
import gameDTO.GameStateDTO;
import view.ServerSocketView;

public class Server {

	private final static int PORT=29999;
	private GameState gioco;
	private Controller controller;
	
	public Server(){
		try {
			this.gioco=new GameState();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.controller=new Controller(gioco);
	}
	
	private void startSocket() throws IOException{
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		ServerSocket serverSocket=new ServerSocket(PORT);
		
		System.out.println("SERVER SOCKET READY ON PORT "+ PORT);

		while(true){
			Socket socket=serverSocket.accept();
			GameStateDTO gameStateDTO=new GameStateDTO();
			gameStateDTO.setCittà(gioco.getCittà());
			gameStateDTO.setConsiglieri(gioco.getConsiglieri());
			gameStateDTO.setRegioni(gioco.getRegioni());
			ServerSocketView view=new ServerSocketView(socket, gameStateDTO);
			this.gioco.registerObserver(view);
			view.registerObserver(this.controller);
			executor.submit(view);
			
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		Server server=new Server();
		server.startSocket();
	}
}
