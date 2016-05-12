package controller;

import controller.Partita;
import game.azioni.Azione;

public interface Stato {

	public default void handleAzione(Partita partita, Azione azione){
		System.out.println("metodo di default Stato");
	}

	
}
