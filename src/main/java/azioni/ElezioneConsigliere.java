package azioni;

import game.Balcone;
import game.Consigliere;
import game.Partita;

public class ElezioneConsigliere extends AzionePrincipale {

	private final Balcone balcone;
	private final Consigliere consigliere;
	

	/**constructor
	 * @param balcone
	 * @param consigliere
	 */
	public ElezioneConsigliere(Partita partita, Balcone balcone, Consigliere consigliere) {
		super(partita);
		this.balcone = balcone;
		this.consigliere = consigliere;
	}
/**
 * Remove and add consigliere from the selected balcony
 * Add consigliereTolto to the tabellone
 * player win 4 coins
 */
	@Override
	public boolean eseguiAzione() {
		Consigliere consigliereTolto = this.balcone.aggiungiConsigliere(consigliere);
		this.partita.getTabellone().getConsiglieri().add(consigliereTolto);
		this.partita.getGiocatoreCorrente().aumentaRicchezza(4);
		return true;
	}
}
