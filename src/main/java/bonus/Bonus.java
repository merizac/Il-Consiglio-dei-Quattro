package bonus;

import game.GameState;

public abstract class Bonus {
	
	/**constructore of Bonus
	 * @param gameState
	 */
	public Bonus() {
		super();
	}
	
	public abstract void usaBonus(GameState gameState);
}
