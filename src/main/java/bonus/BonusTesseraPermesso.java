package bonus;


import java.util.Set;

import game.Partita;

public class BonusTesseraPermesso extends BonusNobiltà {

	public BonusTesseraPermesso(Partita partita) {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> boolean usaBonus(Set<T> oggetti, Partita partita) {
		return false;
		
		
	}

	
	
}
