package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import game.azioni.AcquistoTesseraPermesso;
import game.azioni.Azione;
import game.azioni.CambioTesseraPermesso;
import game.azioni.CostruzioneAiutoRe;
import game.azioni.CostruzioneTesseraPermesso;
import game.azioni.ElezioneConsigliere;
import game.azioni.ElezioneConsigliereVeloce;
import game.azioni.IngaggioAiutante;
import game.azioni.SecondaAzionePrincipale;
import view.ViewCLI;

public class ParserAzione {

	private GameState gameState;

	/**
	 * @param gameState
	 */
	public ParserAzione(GameState gameState) {
		this.gameState = gameState;
	}
	
	public void instanziaGiocatori(String numero){
		gameState.creaGiocatori(Integer.parseInt(numero));
	}
	
	public Azione parser (String input) throws Exception{
		StringTokenizer azione= new StringTokenizer(input);
		Regione regioneSelezionata=null;
		ArrayList<CartaPolitica> cartePolitica= new ArrayList<>();
		Città cittàSelezionata = null;
		TesseraPermesso tesseraPermessoGiocatore=null;
		Consigliere consigliereScelto=null;
		CartaPolitica politica=null;
		int indiceTesseraPermesso=0;
		int counter=0;
		String numeroAzione=azione.nextToken();
		Azione azioneCreata=null;
		
		switch(numeroAzione){
		
		case "1p" :{
			counter++;
			regioneSelezionata=cercaRegione(azione.nextToken()); 
			while(azione.hasMoreTokens() && counter!=azione.countTokens()){
				counter++;
				politica=cercaCartaPolitica(azione.nextToken()) ;
				if(politica==null){
					throw new Exception("carta politica inesistente");
				}
				else
					cartePolitica.add(politica);
			}
			String indiceTessera=azione.nextToken();
			indiceTesseraPermesso=Integer.parseInt(indiceTessera);
			azioneCreata=new AcquistoTesseraPermesso(gameState, cartePolitica, regioneSelezionata, indiceTesseraPermesso ) ;	
		}
		
		case "2p" :{
			cittàSelezionata=cercaCittà(azione.nextToken()); 
			while(azione.hasMoreTokens() ){
				politica=cercaCartaPolitica(azione.nextToken()); 
				if(politica==null){
					throw new Exception("carta politica inesistente");
				}
				else
					cartePolitica.add(politica);
			}
			
			azioneCreata=new CostruzioneAiutoRe(gameState, cittàSelezionata, cartePolitica); 
		}
		
		case "3p":{
			indiceTesseraPermesso=Integer.parseInt(azione.nextToken());
			try{
				tesseraPermessoGiocatore=this.gameState.getGiocatoreCorrente().getTesserePermesso().get(indiceTesseraPermesso); 
			}
			catch(ArrayIndexOutOfBoundsException e  ){
				throw e;
			}
			
			cittàSelezionata=cercaCittà(azione.nextToken());  
			azioneCreata=new CostruzioneTesseraPermesso(gameState, cittàSelezionata, tesseraPermessoGiocatore ); 
		}
		
		case "4p":{
			regioneSelezionata=cercaRegione(azione.nextToken()); 
			System.out.println(regioneSelezionata);
			consigliereScelto=cercaConsigliere(azione.nextToken()); 
			System.out.println(consigliereScelto);
			
			if(consigliereScelto ==null){
				throw new Exception("consigliere inesistente");
			}
			
			azioneCreata=new ElezioneConsigliere(gameState, regioneSelezionata, consigliereScelto); 
		}
		
		case "1v":{
			regioneSelezionata=cercaRegione(azione.nextToken());  
			azioneCreata=new CambioTesseraPermesso(gameState, regioneSelezionata); 
		}
		
		case "2v":{
			regioneSelezionata=cercaRegione(azione.nextToken()); 
			consigliereScelto=cercaConsigliere(azione.nextToken()); 
	
			if(consigliereScelto ==null){
				throw new Exception("consigliere inesistente");
			}
			
			azioneCreata=new ElezioneConsigliereVeloce(gameState, regioneSelezionata, consigliereScelto) ;
		}
		case "3v":{
			azioneCreata=new IngaggioAiutante(gameState);
		}
		case "4v":{
			azioneCreata=new SecondaAzionePrincipale(gameState); 
		}
		
		
	}
		return azioneCreata;	
}
	
	public Regione cercaRegione(String regione){
		Regione regioneSelezionata=null;
		for(Regione r: gameState.getRegioni()){
			if(r.getNome().equals(regione))
				regioneSelezionata=r;
		}
		return regioneSelezionata;
		
	}
	
	public CartaPolitica cercaCartaPolitica(String cartaPolitica){
		CartaPolitica cartaPoliticaCercata=null;
		System.out.println(gameState.getGiocatoreCorrente().getCartePolitica());
		for(CartaPolitica c: gameState.getCartePoliticaGiocatore()){
			if(c.getColore().getColore().equals(cartaPolitica)){
				cartaPoliticaCercata=c;
			}
		}
		return cartaPoliticaCercata;
	}
	
	public Città cercaCittà(String città){
		Città cittàCercata=null;
		for(Città c: this.gameState.getMappa().getGrafo().vertexSet()){
			if(c.getNome().equals(città))
				cittàCercata=c; 
		}
		return cittàCercata;
	}
	
	public Consigliere cercaConsigliere(String consigliere){
		Consigliere consigliereCercato=null;
		for(Consigliere c: gameState.getConsiglieri()){
			if(c.getColore().getColore().equals(consigliere))
				consigliereCercato=c;
			}
		return consigliereCercato;
	}
	
	public static void main(String[] args){
		try {
			GameState gameState=new GameState();
			gameState.creaGiocatori(3);
			//System.out.println(gameState.getGiocatoreCorrente().getCartePolitica());
			ParserAzione parser=new ParserAzione(gameState);
			Scanner scanner=new Scanner(System.in);
			while(true){
				System.out.println("Inserisci azione");
				System.out.println(gameState.getConsiglieri());
				String azione=scanner.nextLine();
				System.out.println(parser.parser(azione.toString()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
}
