package controller;

import game.GameState;

public class StartEnd implements Stato {

	@Override
	public void transizionePrincipale(GameState gameState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transizioneVeloce(GameState gameState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transizionePescaCarta(GameState gameState) {
		gameState.setStato(new Stato11());
		
	}

}
