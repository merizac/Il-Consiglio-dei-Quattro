package controller;

import java.util.List;

import game.GameState;

public class Stato10 implements Stato {

	private List<String> azionePrincipale;
	
	/**
	 * @param azionePrincipale
	 */
	public Stato10() {
		//this.azionePrincipale = riempiAzioniPrincipali();
	}

	@Override
	public void transizionePrincipale(GameState gameState) {
		if(!gameState.isBonusAzionePrincipale()){
			gameState.nextPlayer();
			gameState.setStato(new StartEnd(gameState));
		}
		else
			gameState.setBonusAzionePrincipale(false); 
	}


}
