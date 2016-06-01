package game.azioni;

import game.GameState;
import game.Giocatore;
import game.market.Offerta;
import utility.exception.AzioneNonEseguibile;

public class AzioneAcquisto extends Azione {
	
	private Offerta offerta;
	private Giocatore acquirente;


	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		if(!offerta.getMarketable().acquista(acquirente, offerta))
			 throw new AzioneNonEseguibile("L'acquisto non è effettuabile");
		else{
			gameState.getStato().transizioneOfferta(gameState);
		}
	}


	/**
	 * @return the offerta
	 */
	public Offerta getOfferta() {
		return offerta;
	}


	/**
	 * @param offerta the offerta to set
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
	 * @param acquirente the acquirente to set
	 */
	public void setAcquirente(Giocatore acquirente) {
		this.acquirente = acquirente;
	}

}
