package bonus;

import java.util.Set;

import game.Partita;

public abstract class BonusNobiltà {

	public BonusNobiltà() {
		super();
	}
	
	public abstract <T> boolean usaBonus(Set<T> oggetti, Partita partita);

}
