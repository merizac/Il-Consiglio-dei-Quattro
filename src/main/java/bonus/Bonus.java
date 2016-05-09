package bonus;

import game.Partita;

public abstract class Bonus {
	
	/**constructore of Bonus
	 * @param partita
	 */
	public Bonus() {
		super();
	}
	
	abstract public void usaBonus(Partita partita);
}
