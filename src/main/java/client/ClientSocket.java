package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;


public class ClientSocket {

	private final static int PORT=29999;
	private final static String IP="127.0.0.1";
	private GameStateDTO gameState;
	private ObjectOutputStream socketOut;
	private ObjectInputStream socketIn;
	private GiocatoreDTO giocatore;
	
	public ClientSocket(){
		this.gameState=new GameStateDTO();
		this.giocatore=new GiocatoreDTO();
	}
	
	public void startClient() throws UnknownHostException, IOException {
		Socket socket=new Socket(IP, PORT);
		socketOut=new ObjectOutputStream(socket.getOutputStream());
		socketIn=new ObjectInputStream(socket.getInputStream());
		System.out.println("Connection create");
		
		System.out.println("Inserisci il nome");
		Scanner stdIn= new Scanner(System.in);
		String nome=stdIn.nextLine();
		giocatore.setNome(nome);
		stdIn.close();
		
		socketOut.writeObject(giocatore);
		socketOut.flush();
		System.out.println(giocatore);
		
		ExecutorService executor= Executors.newFixedThreadPool(2);
		executor.submit(new ClientOutHandler(socketOut, gameState, giocatore));
		executor.submit(new ClientInHandler(socketIn, gameState, giocatore));
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
