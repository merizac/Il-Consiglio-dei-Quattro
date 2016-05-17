package game.azioni;

import game.GameState;
import utility.exception.AzioneNonEseguibile;

public abstract class Azione {
	
	protected final GameState gameState;
	
	/**
	 * @param gameState
	 */
	public Azione(GameState gameState) {
		super();
		this.gameState = gameState;
	}

	public abstract void eseguiAzione() throws AzioneNonEseguibile;
}