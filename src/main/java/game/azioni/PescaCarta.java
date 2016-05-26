package game.azioni;

import game.GameState;
import game.notify.AzioniNotify;
import utility.exception.AzioneNonEseguibile;

public class PescaCarta extends Azione {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9127751375818353248L;

	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		gameState.getGiocatoreCorrente().getCartePolitica().add(gameState.getMazzoCartePolitica().pescaCarte());
		gameState.getStato().transizionePescaCarta(gameState);
		gameState.notifyObserver(new AzioniNotify(gameState.getStato().getAzioni()));
	}

}
