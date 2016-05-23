package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import game.azioni.Azione;
import game.query.GetAzioni;
import game.query.Query;


public class ClientOutHandler implements Runnable {

private ObjectOutputStream socketOut;
	
	public ClientOutHandler(ObjectOutputStream socketOut) {
		this.socketOut=socketOut;
	}
	
	@Override
	public void run() {

		System.out.println("RUNNING");
		Scanner stdIn=new Scanner(System.in);
		
		while(true){
			
			
			Query query=new GetAzioni();
			System.out.println("SENDING THE QUERY");
			try{
				socketOut.writeObject(query);
				socketOut.flush();
			}catch(IOException e){
				e.printStackTrace();
			}
			
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
