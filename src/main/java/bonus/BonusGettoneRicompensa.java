package bonus;


import java.util.Set;

import game.Partita;

public class BonusGettoneRicompensa extends BonusNobiltà {

	private final int numeroGettoni;
	
	public BonusGettoneRicompensa(int numeroGettoni) {
		super();
		this.numeroGettoni=numeroGettoni;
	}

	@Override
	public <T> boolean usaBonus(Set<T> oggetti, Partita partita) {
		return false;
				
	}
	
	

	
	
}
