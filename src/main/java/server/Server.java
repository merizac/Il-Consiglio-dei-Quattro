package server;

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
	private GameState gameState;
	private Controller controller;
	
	public Server(){
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

		while(true){
			Socket socket=serverSocket.accept();
			ServerSocketView view=new ServerSocketView(socket, gameState);
			this.gameState.registerObserver(view);
			view.registerObserver(this.controller);
			executor.submit(view);
			
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		Server server=new Server();
		server.startSocket();
	}
}
