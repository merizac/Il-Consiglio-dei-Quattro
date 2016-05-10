package azioni;

import game.Regione;
import game.Consigliere;
import game.Partita;

public class ElezioneConsigliereVeloce extends AzioneVeloce {
	
	private Regione regione;
	private Consigliere consigliere;
	/**
	 * constructor
	 * @param partita
	 * @param regione 
	 * @param consigliere 
	 * @param balcone
	 * @param consigliere
	 */
	public ElezioneConsigliereVeloce(Partita partita, Regione regione, Consigliere consigliere) {
		super(partita);
		this.regione=regione;
		this.consigliere=consigliere;
	}
	/**
	 * check the number of aiutanti and if giocatoreCorrente has 1 aiutante elect a counselor 
	 */
	@Override
	public boolean eseguiAzione() {
		/*PassaggioParametri passaggioParametri = new PassaggioParametri(partita);
		consigliere = passaggioParametri.selezionaConsiglieri();
		regione = passaggioParametri.selezionaRegione();*/
		
		if(partita.getGiocatoreCorrente().getAiutanti().togliAiutanti(1)){
			Consigliere consigliereTolto = this.regione.getBalcone().aggiungiConsigliere(consigliere);
			this.partita.getTabellone().getConsiglieri().add(consigliereTolto);
			return true;
		}
		
		else return false;
	}
}
