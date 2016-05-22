package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import game.azioni.Azione;
import game.query.GetAzioni;
import game.query.Query;
import gameDTO.GameStateDTO;


public class ClientOutHandler implements Runnable {

private ObjectOutputStream socketOut;
private GameStateDTO gameStateDTO;
	
	public ClientOutHandler(ObjectOutputStream socketOut, GameStateDTO gameStateDTO) {
		this.socketOut=socketOut;
		this.gameStateDTO=gameStateDTO;
	}
	
	@Override
	public void run() {

		System.out.println("RUNNING");
		System.out.println("OutHandler :"+gameStateDTO);
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
