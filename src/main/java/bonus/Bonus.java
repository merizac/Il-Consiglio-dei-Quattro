package bonus;

import game.Partita;

public abstract class Bonus {
	private final Partita partita;
	
	/**constructore of Bonus
	 * @param partita
	 */
	public Bonus(Partita partita) {
		this.partita = partita;
	}
	
	/**
	 * @return the partita
	 */
	protected Partita getPartita() {
		return partita;
	}
	
	abstract public void usaBonus();
}
