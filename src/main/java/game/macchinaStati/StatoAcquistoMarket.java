package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import game.GameState;
import game.Giocatore;
import game.notify.AzioniNotify;
import game.notify.OffertaNotify;
import utility.exception.AzioneNonEseguibile;

public class StatoAcquistoMarket implements Stato {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7840008978993076042L;
	private ArrayList<Giocatore> giocatori;
	private List<String> azioni;
	
	public StatoAcquistoMarket(GameState gameState){
		this.azioni=new ArrayList<>();
		this.giocatori=new ArrayList<Giocatore>(gameState.getGiocatori());
		inizializzaStato(gameState);
	}
	
	private void inizializzaStato(GameState gameState){
		riempiAzioni();
		Collections.shuffle(giocatori);
		gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), Arrays.asList(giocatori.get(0))));
		gameState.notifyObserver(new AzioniNotify(azioni, Arrays.asList(giocatori.get(0))));
		
	}
	
	private void riempiAzioni() {
		azioni.add("\nAcquista");
		azioni.add("Passa");
		
	}
	@Override
	public void transizionePassa(GameState gameState) throws AzioneNonEseguibile{
		this.giocatori.remove(0);
		if(!giocatori.isEmpty()){
			Collections.shuffle(giocatori);
			gameState.setStato(this);
			gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), Arrays.asList(giocatori.get(0))));
			gameState.notifyObserver(new AzioniNotify(azioni, Arrays.asList(giocatori.get(0))));
		}
		else{
			gameState.getOfferteMarket().clear();
			gameState.setStato(new StartEnd(gameState));
		}
	}
	
	public void transizioneOfferta(GameState gameState){
		gameState.setStato(this);
		gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), Arrays.asList(giocatori.get(0))));
		gameState.notifyObserver(new AzioniNotify(azioni, Arrays.asList(giocatori.get(0))));
	}
	
	@Override
	public List<String> getAzioni() {
		return azioni;
	}

	/**
	 * @return the giocatori
	 */
	public ArrayList<Giocatore> getGiocatori() {
		return giocatori;
	}
	
	
	

}
