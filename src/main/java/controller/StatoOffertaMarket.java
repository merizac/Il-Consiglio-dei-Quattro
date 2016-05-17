package controller;

import game.GameState;

public class StatoOffertaMarket implements Stato {
	
	@Override
	public void transizionePassa(GameState gameState){
		if(gameState.getNumeroTurni()!=0){
			gameState.decrementaTurno();
			gameState.nextPlayer();
			gameState.setStato(this);
		}
		else
			gameState.setStato(new StatoAcquistoMarket(gameState));
	}
	
	@Override
	public void transizioneOfferta(GameState gameState){
		gameState.setStato(this);
	}

}
