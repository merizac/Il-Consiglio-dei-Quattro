package game;

import mvc.View;
import mvc.ViewCLI;

import java.util.ArrayList;

import azioni.AcquistoTesseraPermesso;
import azioni.CostruzioneAiutoRe;
import azioni.CostruzioneTesseraPermesso;
import azioni.ElezioneConsigliere;
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
	 * @return the view
	 */
	public View getView() {
		return view;
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
		View view= new ViewCLI(this);
		int indiceGiocatore =0;
		
		controllore.registerObserver(view);
		view.registerObserver(controllore);
		
	
		while(true){
			giocatoreCorrente= giocatori.get(indiceGiocatore);
			giocatoreCorrente.aggiungiCartaPolitica(tabellone.getMazzoCartePolitica().pescaCarte());
		
		/*	riempiAzioniPrincipali();
			riempiAzioniVeloci();*/
			
			//mostra gioco e giocatore
			
			String messaggio=view.scegliAzione();
			mostraAzione(messaggio);
			
			if (this.getGiocatoreCorrente().getAzioniPrincipali().isEmpty()){
				String messaggio1 = view.possibilitàAzioneVeloce();
				if (messaggio1.equals("Y")){
					view.scegliAzioneVeloce();
					//controllo ritorno
				}
			}
			else {
				view.scegliAzionePrincipale();
				//controllo ritorno
			}
			if (indiceGiocatore==(giocatori.size()-1)){
				indiceGiocatore=0;
			}
			else indiceGiocatore ++;	
		}
		//condizione fine partita
		
	}

	/*public void riempiAzioniPrincipali() {
		this.giocatoreCorrente.getAzioniPrincipali().add(new ElezioneConsigliere(this));
		this.giocatoreCorrente.getAzioniPrincipali().add(new CostruzioneAiutoRe(this));
		this.giocatoreCorrente.getAzioniPrincipali().add(new CostruzioneTesseraPermesso(this));	
		this.giocatoreCorrente.getAzioniPrincipali().add(new AcquistoTesseraPermesso(this));	
	}
	
	private void riempiAzioniVeloci(){
		this.giocatoreCorrente.getAzioneVeloce().add(new IngaggioAiutante(this));
		this.giocatoreCorrente.getAzioneVeloce().add(new CambioTesseraPermesso(this));
		this.giocatoreCorrente.getAzioneVeloce().add(new ElezioneConsigliereVeloce(this));
		this.giocatoreCorrente.getAzioneVeloce().add(new SecondaAzionePrincipale(this));
	}*/
	
	public void svuotaAzioniVeloci(){
		for (int i = 0; i < this.getGiocatoreCorrente().getAzioneVeloce().size(); i++){
		this.getGiocatoreCorrente().getAzioneVeloce().remove(i);
		}
	}
	
	public void svuotaAzioniPrincipali(){
		for (int i = 0; i < this.getGiocatoreCorrente().getAzioniPrincipali().size(); i++){
		this.getGiocatoreCorrente().getAzioniPrincipali().remove(i);
		}
	}
	
	private void mostraAzione(String messaggio) {
		switch(messaggio){
			case "1":
				view.scegliAzionePrincipale();
				//controllo
				svuotaAzioniPrincipali();
				break;
			case "2":
				view.scegliAzioneVeloce();
				//controllo
				svuotaAzioniVeloci();
				break;
		}
		
	}

}
