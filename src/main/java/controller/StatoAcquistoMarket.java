package controller;

import java.util.ArrayList;
import java.util.Collections;
import game.GameState;
import game.Giocatore;

public class StatoAcquistoMarket implements Stato {
	
	private ArrayList<Giocatore> giocatori;
	
	public StatoAcquistoMarket(GameState gameState){
		this.giocatori=new ArrayList<Giocatore>(gameState.getGiocatori());
	}
	@Override
	public void transizionePassa(GameState gameState){
		
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

}
