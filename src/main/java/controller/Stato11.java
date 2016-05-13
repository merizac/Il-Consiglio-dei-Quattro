package controller;

import game.GameState;
import game.azioni.Azione;

public class Stato11 implements Stato {

	public void handleAzione(GameState gameState, Azione azione) {
	/*	if(azione.eseguiAzione())
			if(!gameState.isBonusAzionePrincipale())
			gameState.setStato(new Stato01());
			else
				gameState.setBonusAzionePrincipale(false);
	}*/
	
/*	public void handleAzione(GameState gameState, AzioneVeloce azione) {
		if(azione.eseguiAzione())
			gameState.setStato(new Stato10());*/
		
	}
	@Override
	public void transizionePrincipale(GameState gameState){
		if(!gameState.isBonusAzionePrincipale()){
		gameState.setStato(new Stato01());
		}
		else
			gameState.setBonusAzionePrincipale(false);
	}
	
	@Override
	public void transizioneVeloce(GameState gameState){
		gameState.setStato(new Stato10());
	}
	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		throw new IllegalArgumentException("Il tipo di azione non può essere eseguita!"); 
		
	}
	@Override
	public void transizionePescaCarta(GameState gameState) {
		throw new IllegalArgumentException("Il tipo di azione non può essere eseguita!"); 
	}

}
