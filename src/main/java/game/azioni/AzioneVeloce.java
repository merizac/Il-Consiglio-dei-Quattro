package game.azioni;

import game.GameState;

public abstract class AzioneVeloce implements Azione {

	protected final GameState gameState;

	/**
	 * @param gameState
	 */
	public AzioneVeloce(GameState gameState) {
		super();
		this.gameState = gameState;
	}
	
	
}
