package game.azioni;

import game.GameState;

public class AzioneNulla extends Azione {

	public AzioneNulla(GameState gameState) {
		super(gameState);
	}
	
	@Override
	public boolean eseguiAzione() {
		gameState.getStato().transizioneVeloce(gameState);
		return true;
	}

}
