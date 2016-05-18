package game.azioni;

import java.util.ArrayList;
import game.Regione;
import game.CartaPolitica;
import game.Colore;
import game.Consigliere;
import game.GameState;
import game.TesseraPermesso;
import game.notify.ErrorParameterNotify;

public class AcquistoTesseraPermesso extends AzionePrincipale {

	private ArrayList<CartaPolitica> carteGiocatore;
	private Regione regione;
	private int indiceTesseraScoperta;
	/**
	 * constructor
	 * @param partita
	 * @param carteGiocatore 
	 * @param carteGiocatore
	 * @param regione 
	 * @param regione
	 * @param indiceTesseraPermesso 
	 */
	public AcquistoTesseraPermesso(GameState gameState, ArrayList<CartaPolitica> carteGiocatore, Regione regione, int indiceTesseraPermesso) {
		super(gameState);
		this.carteGiocatore=carteGiocatore;
		this.regione=regione;
		this.indiceTesseraScoperta=indiceTesseraPermesso;
		}
	/**
	 * check if the color of cards passed are the same of balcone, and check if the player has enough
	 * money to do the action, then subtract the money due from the player
	 */
	@Override
	public void eseguiAzione() {
		/*PassaggioParametri passaggioParametri=new PassaggioParametri(partita);
		carteGiocatore=passaggioParametri.selezionaCarteGiocatore();
		regione=passaggioParametri.selezionaRegione();		
		indiceTesseraScoperta=passaggioParametri.selezionaTesseraScoperta(regione);*/
		
		if(carteGiocatore.isEmpty())
			gameState.notifyObserver(new ErrorParameterNotify("Errore: non sono presenti carte"));
			
		if(!controllaColori())
			gameState.notifyObserver(new ErrorParameterNotify("Errore: i colori delle carte scelte non corrispondono con quelle del balcone!"));
			
		if(!paga(calcolaMonete()))
			gameState.notifyObserver(new ErrorParameterNotify("Errore: i soldi non sono sufficienti!"));
			
		   
		for(CartaPolitica c: carteGiocatore){
		    
		      this.gameState.getGiocatoreCorrente().getCartePolitica().remove(c);
		      this.gameState.getMazzoCartePolitica().aggiungiCarte(carteGiocatore);
		    }
		    TesseraPermesso tesseraScelta = regione.getTesserePermessoScoperte().get(indiceTesseraScoperta);
		    gameState.getGiocatoreCorrente().getTesserePermesso().add(tesseraScelta);
		 
		 setStatoTransizionePrincipale(); 
		
	}
	
	/**
	 * 
	 * @param moneteDovute
	 * @return true if the player can pay the money, false otherwise
	 */
	private boolean paga(int moneteDovute) {
		if(!this.gameState.getGiocatoreCorrente().diminuisciRicchezza(moneteDovute))
			return false;
		return true;
	}

	/**
	 * 
	 * @return how many money the player have to pay
	 */
	private int calcolaMonete() {
		
		int monete=0;
		int carte=carteGiocatore.size();
		for (CartaPolitica carta: carteGiocatore ){
			if (carta.getColore().getColore()=="Multicolore"){
				monete++;
			}
		}
		
		switch (carte){
		case 1:
		 monete=monete+10;
		 break;
		
		case 2:
		 monete=monete+7;
		 break;
		
		case 3:
		 monete=monete+4;
		break;
		
		default: 
		break;
		}
		return monete;
			
	}

	/**
	 * 
	 * @return false if the cards of the player didn't match
	 */
	private boolean controllaColori() {
		ArrayList<Consigliere> copiaConsiglieri = new ArrayList<Consigliere>(regione.getBalcone().getConsigliere());
		for (CartaPolitica carta: carteGiocatore ){
			if (carta.equals(new CartaPolitica(new Colore("Multicolor")))){
				continue;
			}
				
			for (Consigliere consigliere: copiaConsiglieri){
					if (consigliere.getColore().equals(carta.getColore())){
						regione.getBalcone().getConsigliere().remove(consigliere);
						
					}
					
					else
						return false; //se la carta non matcha
				}
			}
		return true;
		
	}

}
