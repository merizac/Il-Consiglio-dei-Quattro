package game.azioni;

import java.util.Arrays;

import game.Balcone;
import game.Consigliere;
import game.GameState;
import game.notify.GameStateNotify;
import game.notify.GiocatoreNotify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.ElezioneConsigliereDTO;

public class ElezioneConsigliere extends AzionePrincipale {

	private final int ID = 1;
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

		gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
		gameState.notifyObserver(
				new GiocatoreNotify(gameState.getGiocatoreCorrente(), Arrays.asList(gameState.getGiocatoreCorrente())));

		setStatoTransizionePrincipale(gameState);
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new ElezioneConsigliereDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElezioneConsigliere other = (ElezioneConsigliere) obj;
		if (ID != other.ID)
			return false;
		return true;
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
