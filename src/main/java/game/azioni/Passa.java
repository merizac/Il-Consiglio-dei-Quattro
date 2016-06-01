package game.azioni;

import game.GameState;
import utility.exception.AzioneNonEseguibile;

public class Passa extends Azione {

	
	@Override
	public void eseguiAzione(GameState gameState) {
		try {
			gameState.getStato().transizionePassa(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
