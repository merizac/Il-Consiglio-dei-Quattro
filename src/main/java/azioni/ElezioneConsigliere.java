package azioni;

import game.Consigliere;
import game.Partita;
import game.Regione;

public class ElezioneConsigliere extends AzionePrincipale {

	private final Regione regione;
	private final Consigliere consigliere;
	

	/**constructor
	 * @param balcone
	 * @param consigliere
	 */
	public ElezioneConsigliere(Partita partita, Regione regione, Consigliere consigliere) {
		super(partita);
		this.regione=regione;
		this.consigliere=consigliere;
	}
/**
 * Remove and add consigliere from the selected balcony
 * Add consigliereTolto to the tabellone
 * player win 4 coins
 */
	@Override
	public boolean eseguiAzione() {
		/*PassaggioParametri passaggioParametri = new PassaggioParametri(partita);
		consigliere = passaggioParametri.selezionaConsiglieri();
		regione = passaggioParametri.selezionaRegione();*/

		Consigliere consigliereTolto = this.regione.getBalcone().aggiungiConsigliere(consigliere);
		this.partita.getTabellone().getConsiglieri().add(consigliereTolto);
		this.partita.getGiocatoreCorrente().aumentaRicchezza(4);
		return true;
	}

}
