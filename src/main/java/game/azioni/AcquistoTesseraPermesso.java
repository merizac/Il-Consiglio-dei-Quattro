package game.azioni;

import java.util.ArrayList;
import java.util.Arrays;

import bonus.Bonus;
import game.Regione;
import game.CartaPolitica;
import game.Colore;
import game.Consigliere;
import game.GameState;
import game.TesseraPermesso;
import game.notify.ErrorNotify;
import game.notify.GameStateNotify;
import game.notify.GiocatoreNotify;
import gameDTO.gameDTO.GiocatoreDTO;

public class AcquistoTesseraPermesso extends AzionePrincipale implements Bonusable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4588947134505180391L;
	private ArrayList<CartaPolitica> carteGiocatore;
	private Regione regione;
	private int indiceTesseraScoperta;

	
	/**
	 * check if the color of cards passed are the same of balcone, and check if the player has enough
	 * money to do the action, then subtract the money due from the player
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		
		if(carteGiocatore.isEmpty()){
			gameState.notifyObserver(new ErrorNotify("Errore: non sono presenti carte", Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}
			
		if(!controllaColori()){
			gameState.notifyObserver(new ErrorNotify("Errore: i colori delle carte scelte non corrispondono con quelle del balcone!", Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}
			
		if(!paga(calcolaMonete(), gameState)){
			gameState.notifyObserver(new ErrorNotify("Errore: i soldi non sono sufficienti!", Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}
			
		   
		for(CartaPolitica c: carteGiocatore){
		    
		      gameState.getGiocatoreCorrente().getCartePolitica().remove(c);
		      gameState.getMazzoCartePolitica().aggiungiCarte(carteGiocatore);
		    }
		System.out.println("Tessera :"+indiceTesseraScoperta);
		TesseraPermesso tesseraScelta = regione.getTesserePermessoScoperte().get(indiceTesseraScoperta);
		regione.getTesserePermessoScoperte().remove(indiceTesseraScoperta);
	    gameState.getGiocatoreCorrente().getTesserePermesso().add(tesseraScelta);
	    
		regione.getTesserePermessoScoperte().add(regione.getMazzoTesserePermesso().pescaCarte());
		
		for (Bonus b:tesseraScelta.getBonus()){
			System.out.println(b);
			b.usaBonus(gameState);
		}
		
		
		
		gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
		gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(), Arrays.asList(gameState.getGiocatoreCorrente())));
		setStatoTransizionePrincipale(gameState); 
		
	}
	
	/**
	 * 
	 * @param moneteDovute
	 * @return true if the player can pay the money, false otherwise
	 */
	private boolean paga(int moneteDovute, GameState gameState) {
		if(!gameState.getGiocatoreCorrente().diminuisciRicchezza(moneteDovute))
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
			boolean ok=false;
			if (carta.equals(new CartaPolitica(new Colore("Multicolore")))){
				continue;
			}
				
			for (Consigliere consigliere: copiaConsiglieri){
					if (!consigliere.getColore().equals(carta.getColore())){
						ok=false;
						continue;
					}
					
					else{
						ok=true;
						copiaConsiglieri.remove(consigliere);
						break;}
				}
			if(!ok) 
				return false; //se la carta non matcha
			}
		return true;
		
	}

	/**
	 * @return the carteGiocatore
	 */
	public ArrayList<CartaPolitica> getCarteGiocatore() {
		return carteGiocatore;
	}

	/**
	 * @param carteGiocatore the carteGiocatore to set
	 */
	public void setCarteGiocatore(ArrayList<CartaPolitica> carteGiocatore) {
		this.carteGiocatore = carteGiocatore;
	}

	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}

	/**
	 * @return the indiceTesseraScoperta
	 */
	public int getIndiceTesseraScoperta() {
		return indiceTesseraScoperta;
	}

	/**
	 * @param indiceTesseraScoperta the indiceTesseraScoperta to set
	 */
	public void setIndiceTesseraScoperta(int indiceTesseraScoperta) {
		if(indiceTesseraScoperta>regione.getTesserePermessoScoperte().size())
			throw new IllegalArgumentException("L'indice della tessera permesso deve essere minore di "+regione.getTesserePermessoScoperte().size());
		if(indiceTesseraScoperta<0)
			throw new IllegalArgumentException("L'indice della tessera permesso deve essere maggiore di 0");
		
		this.indiceTesseraScoperta = indiceTesseraScoperta;
	}

}
