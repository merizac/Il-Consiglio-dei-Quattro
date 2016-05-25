package bonus;

import java.io.Serializable;

import game.GameState;

public abstract class Bonus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3855830326284346402L;

	/**constructore of Bonus
	 * @param gameState
	 */
	public Bonus() {
		super();
	}
	
	public abstract void usaBonus(GameState gameState);
}
