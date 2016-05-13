package controller;

import controller.Partita;
import game.GameState;
import game.azioni.Azione;

public interface Stato {

	public default void handleAzione(Partita partita, Azione azione){
		throw new IllegalArgumentException("Il tipo di azione non è accettato!");
	}
	
	public abstract void transizionePrincipale(GameState gameState);
	
	public abstract void transizioneVeloce(GameState gameState);

	public abstract void transizioneSecondaPrincipale(GameState gameState);
	
	

	
}
