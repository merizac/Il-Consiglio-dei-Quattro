package game.azioni;

import game.GameState;
import game.Giocatore;
import game.macchinaStati.StartEnd;
import game.macchinaStati.StatoAcquistoMarket;
import game.market.Offerta;
import gameDTO.azioniDTO.AzioneDTO;
import utility.exception.AzioneNonEseguibile;

public class AzioneAcquisto extends Azione {

	private final int ID=12;
	private Offerta offerta;
	private Giocatore acquirente;

	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		if (!offerta.getMarketable().acquista(acquirente, offerta))
			throw new AzioneNonEseguibile("L'acquisto non è effettuabile");
		else {
			if (!gameState.getOfferteMarket().isEmpty()) {
				gameState.getOfferteMarket().remove(this.offerta);
				gameState.getStato().transizioneOfferta(gameState);
			}
			else
				gameState.setStato(new StartEnd(gameState));
		}
	}

	/**
	 * @return the offerta
	 */
	public Offerta getOfferta() {
		return offerta;
	}

	/**
	 * @param offerta
	 *            the offerta to set
	 */
	public void setOfferta(Offerta offerta) {
		this.offerta = offerta;
	}

	/**
	 * @return the acquirente
	 */
	public Giocatore getAcquirente() {
		return acquirente;
	}

	/**
	 * @param acquirente
	 *            the acquirente to set
	 */
	public void setAcquirente(Giocatore acquirente) {
		this.acquirente = acquirente;
	}

	@Override
	public boolean isTurno(Giocatore giocatore, GameState gameState) {
		return (giocatore.equals(((StatoAcquistoMarket) gameState.getStato()).getGiocatori().get(0)));
	}

	@Override
	public AzioneDTO getAzioneDTO() {
	return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Acquista  [Acquista]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	/* (non-Javadoc)
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
		AzioneAcquisto other = (AzioneAcquisto) obj;
		if (ID != other.ID)
			return false;
		return true;
	}



}
