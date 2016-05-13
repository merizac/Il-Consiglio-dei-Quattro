package controller;

import game.GameState;

public class Stato10 implements Stato {

	@Override
	public void transizionePrincipale(GameState gameState) {
		if(!gameState.isBonusAzionePrincipale()){
			gameState.setStato(new Stato11());
		}
		else
			gameState.setBonusAzionePrincipale(false); 
	}

	@Override
	public void transizioneVeloce(GameState gameState) {
		throw new IllegalArgumentException("Il tipo di azione non può essere eseguita!"); 
		
	}

	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		throw new IllegalArgumentException("Il tipo di azione non può essere eseguita!"); 
		
	}

	/*public void handleAzione(GameState gameState, AzionePrincipale azione) {
		if(azione.eseguiAzione())
			if(!gameState.isBonusAzionePrincipale()){
				gameState.setStato(new Stato11());
				//cambia giocatore
				//pesca carta
			}
			else
				gameState.setBonusAzionePrincipale(false);
			
	}
	
	public void handleAzione(GameState gameState, AzioneVeloce azione) {
		gameState.notifyObserver(new ErrorNotify());
	
	}*/
	
	
	
	

}
