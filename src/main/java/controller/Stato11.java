package controller;

import controller.Partita;
import game.azioni.Azione;
import game.azioni.AzionePrincipale;
import game.azioni.AzioneVeloce;

public class Stato11 implements Stato {

	
	public void handleAzione(Partita partita, AzionePrincipale azione) {
		if(azione.eseguiAzione())
			partita.setStato(new Stato01());
		
	}
	
	public void handleAzione(Partita partita, AzioneVeloce azione) {
		if(azione.eseguiAzione())
			partita.setStato(new Stato10());
	}
}
