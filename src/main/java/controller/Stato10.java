package controller;

import java.util.List;

import game.GameState;

public class Stato10 extends Stato {

	private List<String> azionePrincipale;
	
	/**
	 * @param azionePrincipale
	 */
	public Stato10() {
		this.azionePrincipale = riempiAzioniPrincipali();
	}

	@Override
	public void transizionePrincipale(GameState gameState) {
		if(!gameState.isBonusAzionePrincipale()){
			gameState.setStato(new StartEnd(gameState));
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

	@Override
	public void transizionePescaCarta(GameState gameState) {
		throw new IllegalArgumentException("Il tipo di azione non può essere eseguita!"); 
		
	}

	/*public void handleAzione(GameState gameState, AzionePrincipale azione) {
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
	
	}*/
	
	
	
	

}
