package game.azioni;

import game.GameState;

public abstract class AzionePrincipale implements Azione {
	protected final GameState gameState;

	/**
	 * @param partita
	 */
	public AzionePrincipale(GameState gameState) {
		super();
		this.gameState = gameState;
	}
	
	
}
