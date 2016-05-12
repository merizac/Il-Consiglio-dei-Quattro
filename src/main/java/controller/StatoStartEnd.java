package controller;

import controller.Partita;
import game.azioni.Azione;
import game.azioni.AzionePrincipale;
import game.azioni.PescaCarta;

public class StatoStartEnd implements Stato {


	public void handleAzione(Partita partita, PescaCarta azione) {
		azione.eseguiAzione();		
	}
}
