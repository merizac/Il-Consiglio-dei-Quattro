package game.azioni;

import game.GameState;

public class AzioneNulla extends Azione {

	public AzioneNulla(GameState gameState) {
		super(gameState);
	}
	
	@Override
	public void eseguiAzione() {
		gameState.getStato().transizioneVeloce(gameState);

	}

}
