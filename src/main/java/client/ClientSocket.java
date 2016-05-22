package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gameDTO.GameStateDTO;

public class ClientSocket {

	private final static int PORT=29999;
	private final static String IP="127.0.0.1";
	private GameStateDTO gameStateDTO;
	private ObjectOutputStream socketOut;
	private ObjectInputStream socketIn;
	
	public void startClient() throws UnknownHostException, IOException {
		Socket socket=new Socket(IP, PORT);
		System.out.println("1a");
		socketOut=new ObjectOutputStream(socket.getOutputStream());
		socketIn=new ObjectInputStream(socket.getInputStream());
		System.out.println("Connection create");
		
		GameStateDTO gameStateDTO=new GameStateDTO();
		
		try {
			gameStateDTO=(GameStateDTO) socketIn.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch(WriteAbortedException e){
			e.printStackTrace();
		}
		ExecutorService executor= Executors.newFixedThreadPool(2);
		
		executor.submit(new ClientOutHandler(socketOut, gameStateDTO));
		executor.submit(new ClientInHandler(socketIn, gameStateDTO));
	}
	public static void main(String[] args) {
	ClientSocket clientSocket= new ClientSocket();
	try {
		clientSocket.startClient();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

}
