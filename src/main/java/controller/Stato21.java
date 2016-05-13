package controller;

import game.GameState;
import game.azioni.AzionePrincipale;

public class Stato21 implements Stato {

	public void handleAzione(GameState gameState, AzionePrincipale azione) {
		if(azione.eseguiAzione())
			gameState.setStato(new Stato11());
	}
	
}
