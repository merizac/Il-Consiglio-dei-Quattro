package controller;

import java.util.List;

import game.ErrorNotify;
import game.GameState;
import game.azioni.AzioneNulla;
import game.azioni.AzionePrincipale;
import game.azioni.AzioneVeloce;
import game.azioni.SecondaAzionePrincipale;

public class Stato01 implements Stato {
	
	//private final List<String> azioniVeloci;
	//private final List<String> azioniPrincipale;

	//cambio giocatore
	//pesca carta
	
	public void handleAzione(GameState gameState, AzionePrincipale azione) {
		gameState.notifyObserver(new ErrorNotify());
		//this.azioniVeloci=riempiAzioniVeloci();
		//this.azioniPrincipali=riempiAzioniPrincipali();
		
	}
	
	private Object riempiAzioniPrincipali() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<String> riempiAzioniVeloci() {
		// TODO Auto-generated method stub
		return null;
	}

	public void handleAzione(GameState gameState, AzioneVeloce azione) {
		if(azione.eseguiAzione())
			gameState.setStato(new Stato11());
		//cambia giocatore
		//pesca carta
	}
	

	public void handleAzione(GameState gameState, AzioneNulla azione) {
		if(azione.eseguiAzione())
			gameState.setStato(new Stato11());
		//cambia giocatore
		//pesca carta
	}
	
	
	public void handleAzione(GameState gameState, SecondaAzionePrincipale azione) {
		if(azione.eseguiAzione())
			gameState.setStato(new Stato10());
	}
	
}
