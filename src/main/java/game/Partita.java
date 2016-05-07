package game;

import mvc.View;

import java.util.ArrayList;

import mvc.Controllore;

public class Partita {

	private Tabellone tabellone;
	private ArrayList<Giocatore> giocatori;
	private Giocatore giocatoreCorrente;
	private Controllore controllore;
	private View view;
	/**
	 * @return the tabellone
	 */
	public Tabellone getTabellone() {
		return tabellone;
	}

	/**
	 * @return the giocatoreCorrente
	 */
	public Giocatore getGiocatoreCorrente() {
		return giocatoreCorrente;
	}

	/**
	 * @param giocatoreCorrente the giocatoreCorrente to set
	 */
	public void setGiocatoreCorrente(Giocatore giocatoreCorrente) {
		this.giocatoreCorrente = giocatoreCorrente;
	}

	public void gestisciPartita() {
		
		Controllore controllore= new Controllore(this);
		View view= new View(this);
		
		controllore.registerObserver(view);
		view.registerObserver(controllore);
		
		while(true){
			giocatoreCorrente= giocatori.get(0);
			giocatoreCorrente.aggiungiCartaPolitica(tabellone.getMazzoCartePolitica().pescaCarte());
		
			
			//mostra gioco e giocatore
			
			String messaggio=view.scegliAzione();
			
			mostraAzione(messaggio);
			
			
		}
		
		
	}

	private void mostraAzione(String messaggio) {
		switch(messaggio){
			case "1":
				view.scegliAzionePrincipale();
				break;
			case "2":
				//view.scegliAzioneVeloce();
				break;
		}
		
	}
}
