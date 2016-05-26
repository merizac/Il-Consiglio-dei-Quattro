package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import game.GameState;
import game.Giocatore;
import utility.exception.AzioneNonEseguibile;

public class StatoAcquistoMarket implements Stato {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7840008978993076042L;
	private ArrayList<Giocatore> giocatori;
	private List<String> azioni;
	
	public StatoAcquistoMarket(GameState gameState){
		this.giocatori=new ArrayList<Giocatore>(gameState.getGiocatori());
		riempiAzioni();
	}
	private void riempiAzioni() {
		azioni.add("Acquista");
		azioni.add("Passa");
		
	}
	@Override
	public void transizionePassa(GameState gameState) throws AzioneNonEseguibile{
		
		if(!giocatori.isEmpty()){
			Collections.shuffle(giocatori);
			gameState.setGiocatoreCorrente(giocatori.remove(0));
			gameState.setStato(this);
		}
		else{
			gameState.getOfferteMarket().clear();
			gameState.setStato(new StartEnd(gameState));
		}
	}
	
	public void transizioneOfferta(GameState gameState){
		gameState.setStato(this);
	}
	
	@Override
	public List<String> getAzioni() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
