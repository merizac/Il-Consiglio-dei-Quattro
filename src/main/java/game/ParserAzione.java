package game;

import java.util.ArrayList;
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

public class ParserAzione {

	private GameState gameState;

	/**
	 * @param gameState
	 */
	public ParserAzione(GameState gameState) {
		this.gameState = gameState;
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
		if(azione.nextToken().equals("1p")){
			counter++;
			String regione=azione.nextToken();
			for(Regione r: gameState.getRegioni()){
				if(r.getNome().equals(regione))
					regioneSelezionata=r;
			}
			while(azione.hasMoreTokens() && counter!=azione.countTokens()){
				counter++;
				for(CartaPolitica c: gameState.getCartePoliticaGiocatore()){
					if(c.getColore().getColore().equals(azione.nextToken())){
						politica=c;
					}
				}
				if(politica==null){
					throw new Exception("carta politica inesistente");
				}
				else
					cartePolitica.add(politica);
			}
			String indiceTessera=azione.nextToken();
			indiceTesseraPermesso=Integer.parseInt(indiceTessera);
			return new AcquistoTesseraPermesso(gameState, cartePolitica, regioneSelezionata, indiceTesseraPermesso ) ;	
		}
		
		else if(azione.nextToken().equals("2p"))
		{
			String città=azione.nextToken();
			for(Città c: this.gameState.getMappa().getGrafo().vertexSet()){
				if(c.getNome().equals(città))
					cittàSelezionata=c; 
			}
		
			while(azione.hasMoreTokens() ){
				counter++;
				for(CartaPolitica c: gameState.getCartePoliticaGiocatore()){
					if(c.getColore().getColore().equals(azione.nextToken())){
						politica=c;
					}
				}
				if(politica==null){
					throw new Exception("carta politica inesistente");
				}
				else
					cartePolitica.add(politica);
			}
			
			return new CostruzioneAiutoRe(gameState, cittàSelezionata, cartePolitica); 
		}
		
		else if(azione.nextToken().equals("3p")){
			indiceTesseraPermesso=Integer.parseInt(azione.nextToken());
			try{
				tesseraPermessoGiocatore=this.gameState.getGiocatoreCorrente().getTesserePermesso().get(indiceTesseraPermesso); 
			}
			catch(ArrayIndexOutOfBoundsException e  ){
				throw e;
			}
			
			while(azione.hasMoreTokens() ){
				counter++;
				for(CartaPolitica c: gameState.getCartePoliticaGiocatore()){
					if(c.getColore().getColore().equals(azione.nextToken())){
						politica=c;
					}
				}
				if(politica==null){
					throw new Exception("carta politica inesistente");
				}
				else
					cartePolitica.add(politica);
			}
			
			return new CostruzioneTesseraPermesso(gameState, cittàSelezionata, tesseraPermessoGiocatore ); 
			
		}
		
		else if(azione.nextToken().equals("4p")){
			String regione=azione.nextToken();
			for(Regione r: gameState.getRegioni()){
				if(r.getNome().equals(regione))
					regioneSelezionata=r;
			}
			
			while(azione.hasMoreTokens() ){
				counter++;
				for(Consigliere c: gameState.getConsiglieri()){
					if(c.getColore().getColore().equals(azione.nextToken())){
						consigliereScelto=c;
					}
				}
				if(consigliereScelto ==null){
					throw new Exception("consigliere inesistente");
				}
			}

			return new ElezioneConsigliere(gameState, regioneSelezionata, consigliereScelto); 
		}
		
		else if(azione.nextToken().equals("1v")){
			String regione=azione.nextToken();
			for(Regione r: gameState.getRegioni()){
				if(r.getNome().equals(regione))
					regioneSelezionata=r;
			}
			return new CambioTesseraPermesso(gameState, regioneSelezionata); 
		}
		
		else if(azione.nextToken().equals("2v")){
			String regione=azione.nextToken();
			for(Regione r: gameState.getRegioni()){
				if(r.getNome().equals(regione))
					regioneSelezionata=r;
			}
			
			while(azione.hasMoreTokens() ){
				counter++;
				for(Consigliere c: gameState.getConsiglieri()){
					if(c.getColore().getColore().equals(azione.nextToken())){
						consigliereScelto=c;
					}
				}
				if(consigliereScelto ==null){
					throw new Exception("consigliere inesistente");
				}
			}
			
			return new ElezioneConsigliereVeloce(gameState, regioneSelezionata, consigliereScelto) ;
		}
		
		else if(azione.nextToken().equals("3v")){
			return new IngaggioAiutante(gameState);
		}
		
		else
			return new SecondaAzionePrincipale(gameState); 
			
	}
	
	
}
