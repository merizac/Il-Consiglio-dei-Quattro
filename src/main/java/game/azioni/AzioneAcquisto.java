package game.azioni;

import game.GameState;
import game.market.Offerta;
import utility.exception.AzioneNonEseguibile;

public class AzioneAcquisto extends Azione {
	
	private Offerta offerta;


	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		if(!offerta.getMarketable().acquista(gameState.getGiocatoreCorrente(), offerta))
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

}
