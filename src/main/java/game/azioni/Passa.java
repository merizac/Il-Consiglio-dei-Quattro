package game.azioni;

import game.GameState;
import utility.exception.AzioneNonEseguibile;

public class Passa extends Azione {

	public Passa(GameState gameState) {
		super(gameState);
	}
	
	@Override
	public void eseguiAzione() {
		try {
			gameState.getStato().transizioneVeloce(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
