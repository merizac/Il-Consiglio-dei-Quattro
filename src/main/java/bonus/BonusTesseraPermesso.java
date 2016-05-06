package bonus;


import java.util.Set;

import game.Partita;

public class BonusTesseraPermesso extends BonusNobiltà {

	public BonusTesseraPermesso(Partita partita) {
		super(partita);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> boolean usaBonus(Set<T> oggetti) {
		return false;
		
		
	}

	
	
}
