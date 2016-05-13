package controller;


import game.ErrorNotify;
import game.GameState;
import game.azioni.AzionePrincipale;
import game.azioni.AzioneVeloce;

public class Stato10 implements Stato {

	public void handleAzione(GameState gameState, AzionePrincipale azione) {
		if(azione.eseguiAzione())
			if(!gameState.isBonusAzionePrincipale()){
				gameState.setStato(new Stato11());
				//cambia giocatore
				//pesca carta
				gameState.getGiocatoreCorrente().getCartePolitica().add(gameState.getMazzoCartePolitica().pescaCarte());
			}
			else
				gameState.setBonusAzionePrincipale(false);
			
	}
	
	public void handleAzione(GameState gameState, AzioneVeloce azione) {
		gameState.notifyObserver(new ErrorNotify());
	
	}
	
	

}
