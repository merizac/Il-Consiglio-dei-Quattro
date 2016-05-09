package mvc;

import azioni.*;
import game.Partita;

public class Controllore extends Observable implements Observer {

	private final Partita partita;
	
	/**
	 * @param partita
	 */
	public Controllore(Partita partita) {
		this.partita = partita;
	}
	
	
	@Override
	public void update() {
		
		
	}

	@Override
	public <C> void update(C change) {
		switch((String) change){
			case "1p":
				AzionePrincipale elezioneConsigliere = this.partita.getGiocatoreCorrente().getAzioniPrincipali().get(0);
				//elezioneConsigliere.eseguiAzione();
				//controllo ritorno
				// svuoto array
				break;
			case "2p":
				AzionePrincipale costruzioneAiutoRe = this.partita.getGiocatoreCorrente().getAzioniPrincipali().get(1);
				break;
			case "3p":
				AzionePrincipale costruzioneTesseraPermesso = this.partita.getGiocatoreCorrente().getAzioniPrincipali().get(2);
				break;
			case "4p":
				AzionePrincipale acquistoTesseraPermesso = this.partita.getGiocatoreCorrente().getAzioniPrincipali().get(3);
				break;
			case "1v":
				AzioneVeloce ingaggioAiutante = this.partita.getGiocatoreCorrente().getAzioneVeloce().get(0);
				break;
			case "2v":
				AzioneVeloce cambioTesseraPermesso = this.partita.getGiocatoreCorrente().getAzioneVeloce().get(1);
				break;
			case "3v":
				AzioneVeloce elezioneConsigliereVeloce = this.partita.getGiocatoreCorrente().getAzioneVeloce().get(2);
				break;
			case "4v":
				AzioneVeloce secondaAzionePrincipale = this.partita.getGiocatoreCorrente().getAzioneVeloce().get(3);
				break;
		}
			
	}



	
}
