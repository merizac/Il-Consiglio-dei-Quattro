package bonus;

import game.Partita;

public abstract class Bonus {
	protected final Partita partita;
	
	/**constructore of Bonus
	 * @param partita
	 */
	public Bonus(Partita partita) {
		this.partita = partita;
	}
	
	abstract public void usaBonus();
}
