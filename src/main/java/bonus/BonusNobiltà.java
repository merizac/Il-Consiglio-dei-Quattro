package bonus;

import java.util.Set;

import game.Partita;

public abstract class BonusNobiltà {

	protected final Partita partita;
	public BonusNobiltà(Partita partita) {
		this.partita=partita;
	}
	
	public abstract <T> boolean usaBonus(Set<T> oggetti);

}
