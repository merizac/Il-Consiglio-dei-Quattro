package bonus;


import java.util.Set;

import game.Partita;

public class BonusGettoneRicompensa extends BonusNobiltà {

	private final int numeroGettoni;
	
	public BonusGettoneRicompensa(Partita partita, int numeroGettoni) {
		super(partita);
		this.numeroGettoni=numeroGettoni;
	}

	@Override
	public <T> boolean usaBonus(Set<T> oggetti) {
		return false;
				
	}
	
	

	
	
}
