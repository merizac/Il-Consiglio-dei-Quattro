package controller;

import controller.Partita;
import game.ErrorNotify;
import game.azioni.Azione;
import game.azioni.AzionePrincipale;
import game.azioni.AzioneVeloce;
import game.azioni.SecondaAzionePrincipale;

public class Stato10 implements Stato {

	public void handleAzione(Partita partita, AzionePrincipale azione) {
		if(azione.eseguiAzione())
			partita.setStato(new StatoStartEnd());
			//cambia giocatore
		
	}
	
	public void handleAzione(Partita partita, AzioneVeloce azione) {
		partita.getGameState().notifyObserver(new ErrorNotify());
	
	}
	
	

}
