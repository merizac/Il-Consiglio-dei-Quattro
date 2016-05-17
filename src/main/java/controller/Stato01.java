package controller;

import java.util.List;

import game.GameState;

public class Stato01 extends Stato {
	
	private final List<String> azioniVeloci;

	
	
	/**
	 * @param azioniVeloci
	 * @param azioniPrincipale
	 */
	public Stato01() {
		this.azioniVeloci = riempiAzioniVeloci();
		
	}

	/*public void handleAzione(GameState gameState, AzionePrincipale azione) {
		gameState.notifyObserver(new ErrorNotify());
		//this.azioniVeloci=riempiAzioniVeloci();
		//this.azioniPrincipali=riempiAzioniPrincipali();
		
	}*/
	
	
	/*private Object riempiAzioniPrincipali() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<String> riempiAzioniVeloci() {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public void transizionePrincipale(GameState gameState) {
		throw new IllegalArgumentException("Il tipo di azione non può essere eseguita!"); 
	}

	@Override
	public void transizioneVeloce(GameState gameState) {
		gameState.setStato(new StartEnd(gameState));
	}

	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		gameState.setStato(new Stato10());
		
	}

	@Override
	public void transizionePescaCarta(GameState gameState) {
		throw new IllegalArgumentException("Il tipo di azione non può essere eseguita!"); 
		
	}

	/*public void handleAzione(GameState gameState, AzioneVeloce azione) {
		if(azione.eseguiAzione())
			gameState.setStato(new Stato11());
		//cambia giocatore
		//pesca carta
		gameState.getGiocatoreCorrente().getCartePolitica().add(gameState.getMazzoCartePolitica().pescaCarte());
	}
	

	public void handleAzione(GameState gameState, AzioneNulla azione) {
		if(azione.eseguiAzione())
			gameState.setStato(new Stato11());
		//cambia giocatore
		//pesca carta
		gameState.getGiocatoreCorrente().getCartePolitica().add(gameState.getMazzoCartePolitica().pescaCarte());
	}
	
	
	public void handleAzione(GameState gameState, SecondaAzionePrincipale azione) {
		if(azione.eseguiAzione())
			gameState.setStato(new Stato10());
	}*/
	
}
