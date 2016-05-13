package controller;

import controller.Partita;
import game.azioni.Azione;

public interface Stato {

	public default void handleAzione(Partita partita, Azione azione){
		throw new IllegalArgumentException("Il tipo di azione non è accettato!");
	}

	
}
