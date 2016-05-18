package it.polimi.ingsw.cg17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import controller.Partita;
import game.CartaPolitica;
import game.Città;
import game.Consigliere;
import game.GameState;
import game.Giocatore;
import game.ParserAzione;
import game.Regione;
import game.azioni.AcquistoTesseraPermesso;
import game.azioni.Azione;
import game.azioni.CambioTesseraPermesso;
import game.azioni.CostruzioneAiutoRe;
import game.azioni.ElezioneConsigliere;
import game.azioni.IngaggioAiutante;
import game.azioni.PescaCarta;
import utility.exception.AzioneNonEseguibile;
import view.View;
import view.ViewCLI;

public class MainProva {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		GameState gameState=null;
		Reader reader=new Reader();
		try{
		gameState=new GameState();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		/*ParserAzione parser=new ParserAzione(gameState);
		View view= new ViewCLI(gameState, parser);
		new Partita(gameState, view);
		Scanner scanner = new Scanner(System.in);
		 view.input(scanner);
		 
		 while(true){
			 System.out.println("Inserisci azione");
			 String command=scanner.nextLine();
		 }*/
		gameState.creaGiocatori(3);
		
		Azione azione2=new PescaCarta(gameState);
		try {
			azione2.eseguiAzione();
		} catch (AzioneNonEseguibile e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Città prova=null;
		for(Città c: gameState.getMappa().getGrafo().vertexSet())
		{
			if(c.getNome().equals("Osium"))
				prova=c;
		}
		ArrayList<CartaPolitica> carteGiocatore=new ArrayList<CartaPolitica>();
		HashSet<Consigliere> consiglieri=new HashSet<Consigliere>(gameState.getPlanciaRe().getBalconeRe().getConsigliere()) ;

		for(Consigliere c: consiglieri){
			carteGiocatore.add(new CartaPolitica(c.getColore()));
		}
		System.out.println("prima"+gameState.getGiocatoreCorrente());
		Azione azione=new CostruzioneAiutoRe(gameState, prova, carteGiocatore); 
		try {
			azione.eseguiAzione();
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("dopo"+gameState.getGiocatoreCorrente());
		System.out.println(gameState.getStato());
		
	}

}
