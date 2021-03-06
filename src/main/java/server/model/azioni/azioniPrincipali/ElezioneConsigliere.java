package server.model.azioni.azioniPrincipali;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.ElezioneConsigliereDTO;
import server.model.game.Balcone;
import server.model.game.Consigliere;
import server.model.game.GameState;

public class ElezioneConsigliere extends AzionePrincipale {

	private Balcone balcone;
	private Consigliere consigliere;

	/**
	 * @param regione
	 *            the regione to set
	 */

	public void setBalcone(Balcone balcone) {
		if (balcone == null)
			throw new NullPointerException("La regione deve essere definita");
		this.balcone = balcone;
	}

	/**
	 * @param consigliere
	 *            the consigliere to set
	 */
	public void setConsigliere(Consigliere consigliere) {
		if (consigliere == null)
			throw new NullPointerException("La regione deve essere definita");
		this.consigliere = consigliere;
	}

	/**
	 * Remove and add consigliere from the selected balcony Add consigliereTolto
	 * to the tabellone player win 4 coins
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		Consigliere consigliereTolto = this.balcone.aggiungiConsigliere(consigliere);
		gameState.getConsiglieri().remove(consigliere);
		gameState.getConsiglieri().add(consigliereTolto);
		gameState.getGiocatoreCorrente().aumentaRicchezza(4);

		notify(gameState);

		setStatoTransizionePrincipale(gameState);
	}

	/**
	 * @return dto action
	 */
	@Override
	public AzioneDTO getAzioneDTO() {
		return new ElezioneConsigliereDTO();
	}


	/**
	 * @return the balcone
	 */
	public Balcone getBalcone() {
		return balcone;
	}

	/**
	 * @return the consigliere
	 */
	public Consigliere getConsigliere() {
		return consigliere;
	}

}
