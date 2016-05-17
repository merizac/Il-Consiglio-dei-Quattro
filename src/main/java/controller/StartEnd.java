package controller;

import game.GameState;

public class StartEnd implements Stato {


	public StartEnd(GameState gameState) {
		gameState.nextPlayer();
		if(gameState.getNumeroTurni()!=gameState.getGiocatori().size()){
			gameState.prossimoTurno();
		}
		else
			gameState.setStato(new StatoOffertaMarket());
	}

	@Override
	public void transizionePescaCarta(GameState gameState) {
		gameState.setStato(new Stato11());
		
	}

}
