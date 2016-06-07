package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.azioni.AzioneAcquisto;
import game.azioni.Passa;
import game.notify.AzioniNotify;
import game.notify.OffertaNotify;
import utility.exception.AzioneNonEseguibile;

public class StatoAcquistoMarket implements Stato {

	private ArrayList<Giocatore> giocatori;
	
	public StatoAcquistoMarket(GameState gameState){
		this.giocatori=new ArrayList<Giocatore>(gameState.getGiocatori());
		inizializzaStato(gameState);
	}
	
	private void inizializzaStato(GameState gameState){
		Collections.shuffle(giocatori);
		gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), Arrays.asList(giocatori.get(0))));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(giocatori.get(0))));
		
	}

	@Override
	public void transizionePassa(GameState gameState) throws AzioneNonEseguibile{
		this.giocatori.remove(0);
		if(!giocatori.isEmpty()){
			Collections.shuffle(giocatori);
			gameState.setStato(this);
			gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), Arrays.asList(giocatori.get(0))));
			gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(giocatori.get(0))));
		}
		else{
			gameState.getOfferteMarket().clear();
			gameState.setStato(new StartEnd(gameState));
		}
	}
	
	public void transizioneOfferta(GameState gameState){
		gameState.setStato(this);
		gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), Arrays.asList(giocatori.get(0))));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(giocatori.get(0))));
	}
	
	@Override
	public List<Azione> getAzioni() {
		return Arrays.asList(new AzioneAcquisto(), new Passa());
	}

	/**
	 * @return the giocatori
	 */
	public ArrayList<Giocatore> getGiocatori() {
		return giocatori;
	}
	
	
	

}
