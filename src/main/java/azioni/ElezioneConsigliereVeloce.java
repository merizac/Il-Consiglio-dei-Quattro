package azioni;

import game.Balcone;
import game.Consigliere;
import game.Partita;

public class ElezioneConsigliereVeloce extends AzioneVeloce {
	
	private final Balcone balcone;
	private final Consigliere consigliere;
	/**
	 * constructor
	 * @param partita
	 * @param balcone
	 * @param consigliere
	 */
	public ElezioneConsigliereVeloce(Partita partita, Balcone balcone, Consigliere consigliere) {
		super(partita);
		this.balcone = balcone;
		this.consigliere = consigliere;
	}
	/**
	 * check the number of aiutanti and if giocatoreCorrente has 1 aiutante elect a counselor 
	 */
	@Override
	public boolean eseguiAzione() {
		if(partita.getGiocatoreCorrente().getAiutanti().togliAiutanti(1)){
			Consigliere consigliereTolto = this.balcone.aggiungiConsigliere(consigliere);
			this.partita.getTabellone().getConsiglieri().add(consigliereTolto);
			return true;
		}
		
		else return false;
	}
}
