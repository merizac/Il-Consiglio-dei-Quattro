package controller;

import controller.Partita;
import game.ErrorNotify;
import game.azioni.AzionePrincipale;
import game.azioni.AzioneVeloce;
import game.azioni.SecondaAzionePrincipale;

public class Stato01 implements Stato {

	public void handleAzione(Partita partita, AzionePrincipale azione) {
		partita.getGameState().notifyObserver(new ErrorNotify());
		
	}
	
	public void handleAzione(Partita partita, AzioneVeloce azione) {
		if(azione.eseguiAzione())
			partita.setStato(new StatoStartEnd());
	}
	
	public void handleAzione(Partita partita, SecondaAzionePrincipale azione) {
		if(azione.eseguiAzione())
			partita.setStato(new Stato10());
	}
	
}
