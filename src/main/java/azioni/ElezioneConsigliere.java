package azioni;

import game.Consigliere;
import game.GameState;
import game.Regione;

public class ElezioneConsigliere extends AzionePrincipale {

	private final Regione regione;
	private final Consigliere consigliere;
	

	/**constructor
	 * @param balcone
	 * @param consigliere
	 */
	public ElezioneConsigliere(GameState gameState, Regione regione, Consigliere consigliere) {
		super(gameState);
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
		/*PassaggioParametri passaggioParametri = new PassaggioParametri(gameState);
		consigliere = passaggioParametri.selezionaConsiglieri();
		regione = passaggioParametri.selezionaRegione();*/

		Consigliere consigliereTolto = this.regione.getBalcone().aggiungiConsigliere(consigliere);
		this.gameState.getConsiglieri().add(consigliereTolto);
		this.gameState.getGiocatoreCorrente().aumentaRicchezza(4);
		return true;
	}

}
