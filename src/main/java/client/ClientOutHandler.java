package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.azioni.ElezioneConsigliere;
import game.azioni.PescaCarta;


public class ClientOutHandler implements Runnable {

private ObjectOutputStream socketOut;
private GameState gameState;
private Giocatore giocatore;
	
	public ClientOutHandler(ObjectOutputStream socketOut, GameState gameState, Giocatore giocatore) {
		this.socketOut=socketOut;
		this.gameState=gameState;
		this.giocatore=giocatore;
	}
	
	@Override
	public void run() {

		System.out.println("RUNNING");
		//System.out.println("OutHandler :"+gameState);
		Scanner stdIn=new Scanner(System.in);
		
		while(true){
			Azione action=null;
			System.out.println("entrato");
			System.out.println(giocatore);
			String inputLine=stdIn.nextLine();
			System.out.println(inputLine);
			System.out.println(giocatore);
			System.out.println(gameState.getGiocatoreCorrente());
			
			
			
			if(giocatore.equals(gameState.getGiocatoreCorrente())){
				if(inputLine.equals("pesca")){
					
				}
					action=new PescaCarta();
				if(inputLine.equals("elezione consigliere")){
					ElezioneConsigliere elezione=new ElezioneConsigliere();
					System.out.println(gameState.getConsiglieri());
					System.out.println("scegli consigliere");
					String consigliere=stdIn.nextLine();
					elezione.setConsigliere(gameState.getConsiglieri().iterator().next());
					System.out.println(gameState.getRegioni());
					String regione=stdIn.nextLine();
					elezione.setRegione(gameState.getRegioni().get(0));
					action=elezione;
				
				}
				System.out.println("SENDING THE ACTION");
				try{
					socketOut.writeObject(action);
					socketOut.flush();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			}
			
			else
				System.out.println("Non è il tuo turno");
			
			
			
	}
	}

}
