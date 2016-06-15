package server.model.bonus;

import java.io.Serializable;

import server.model.game.GameState;

public abstract class Bonus implements Serializable {
	

	/**
	 * serializable
	 */
	private static final long serialVersionUID = -3855830326284346402L;
	
	public abstract void usaBonus(GameState gameState);
}
