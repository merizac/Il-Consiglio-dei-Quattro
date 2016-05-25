package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;


public class ClientOutHandler implements Runnable {

private ObjectOutputStream socketOut;
private GameState gameState;
private Giocatore giocatore;
	
	public ClientOutHandler(ObjectOutputStream socketOut, GameState gameState) {
		this.socketOut=socketOut;
		this.gameState=gameState;
	}
	
	@Override
	public void run() {

		System.out.println("RUNNING");
		System.out.println("OutHandler :"+gameState);
		Scanner stdIn=new Scanner(System.in);
		
		while(true){
			
			String inputLine=stdIn.nextLine();
			Azione action=null;
			
		System.out.println("SENDING THE ACTION");
		try{
			socketOut.writeObject(action);
			socketOut.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	}

}
