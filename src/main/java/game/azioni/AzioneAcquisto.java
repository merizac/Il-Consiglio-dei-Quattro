package game.azioni;

import game.GameState;
import game.Giocatore;
import game.macchinaStati.StartEnd;
import game.macchinaStati.StatoAcquistoMarket;
import game.market.Offerta;
import gameDTO.azioniDTO.AzioneAcquistoDTO;
import gameDTO.azioniDTO.AzioneDTO;
import utility.exception.AzioneNonEseguibile;

public class AzioneAcquisto extends Azione {

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

}
