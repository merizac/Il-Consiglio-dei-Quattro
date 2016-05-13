package game.azioni;

import game.GameState;

public abstract class Azione {
	
	protected final GameState gameState;
	
	/**
	 * @param gameState
	 */
	public Azione(GameState gameState) {
		super();
		this.gameState = gameState;
	}

	public abstract boolean eseguiAzione();
}
