package controller;

import java.util.List;

import game.GameState;

public class Stato01 implements Stato {
	
	private List<String> azioniVeloci;

	public Stato01() {
		//this.azioniVeloci = riempiAzioniVeloci();
		
	}

	@Override
	public void transizioneVeloce(GameState gameState) {
		gameState.nextPlayer();
		gameState.setStato(new StartEnd(gameState));
	}

	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		gameState.setStato(new Stato10());
		
	}

	
}
