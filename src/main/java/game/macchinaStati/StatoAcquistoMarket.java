package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import game.GameState;
import game.Giocatore;
import game.notify.AzioniNotify;
import utility.exception.AzioneNonEseguibile;

public class StatoAcquistoMarket implements Stato {

	private ArrayList<Giocatore> giocatori;
	private List<String> azioni;
	
	public StatoAcquistoMarket(GameState gameState){
		this.azioni=new ArrayList<>();
		this.giocatori=new ArrayList<Giocatore>(gameState.getGiocatori());
		System.out.println("acquisto giocatorecorrente: "+ gameState.getGiocatoreCorrente().getNome());
		inizializzaStato(gameState);
	}
	
	private void inizializzaStato(GameState gameState){
		riempiAzioni();
		Collections.shuffle(giocatori);
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), 
				Arrays.asList(giocatori.get(0))));
		
	}
	
	private void riempiAzioni() {
		azioni.add("\nAcquista");
		azioni.add("Passa");
		
	}
	@Override
	public void transizionePassa(GameState gameState) throws AzioneNonEseguibile{
		System.out.println("acquisto giocatorecorrente: "+ gameState.getGiocatoreCorrente().getNome());
		this.giocatori.remove(0);
		if(!giocatori.isEmpty()){
			Collections.shuffle(giocatori);
			gameState.setStato(this);
			gameState.notifyObserver(new AzioniNotify(azioni, Arrays.asList(giocatori.get(0))));
		}
		else{
			System.out.println("Giocatore corrente fine market :"+gameState.getGiocatoreCorrente().getNome());
			gameState.getOfferteMarket().clear();
			gameState.setStato(new StartEnd(gameState));
		}
	}
	
	public void transizioneOfferta(GameState gameState){
		System.out.println("market giocatorecorrente: "+ gameState.getGiocatoreCorrente().getNome());
		gameState.setStato(this);
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
