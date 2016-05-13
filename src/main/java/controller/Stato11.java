package controller;


import game.GameState;
import game.azioni.AzionePrincipale;
import game.azioni.AzioneVeloce;

public class Stato11 implements Stato {

	
	public void handleAzione(GameState gameState, AzionePrincipale azione) {
		if(azione.eseguiAzione())
			if(!gameState.isBonusAzionePrincipale())
			gameState.setStato(new Stato01());
			else
				gameState.setBonusAzionePrincipale(false);
	}
	
	public void handleAzione(GameState gameState, AzioneVeloce azione) {
		if(azione.eseguiAzione())
			gameState.setStato(new Stato10());
	}

}
