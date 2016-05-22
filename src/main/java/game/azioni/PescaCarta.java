package game.azioni;

import game.GameState;
import utility.exception.AzioneNonEseguibile;

public class PescaCarta extends Azione {

	@Override
	public void eseguiAzione(GameState gameState) {
		gameState.getGiocatoreCorrente().getCartePolitica().add(gameState.getMazzoCartePolitica().pescaCarte());
		try {
			gameState.getStato().transizionePescaCarta(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
