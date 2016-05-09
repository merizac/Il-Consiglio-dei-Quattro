package bonus;


import java.util.ArrayList;
import java.util.Set;

import azioni.PassaggioParametri;
import game.CittàBonus;
import game.Partita;

public class BonusGettoneRicompensa extends Bonus {

	private final int numeroGettoni;
	private ArrayList<CittàBonus> cittàGiocatore;
	
	public BonusGettoneRicompensa(int numeroGettoni) {
		super();
		this.numeroGettoni=numeroGettoni;
	}

	@Override
	public void usaBonus(Partita partita) {
		
		PassaggioParametri passaggioParametri = new PassaggioParametri(partita);
		if(numeroGettoni==1)
			cittàGiocatore.add((CittàBonus)passaggioParametri.scegliCittàEmporio());
		else
			return;
		
	}
	
	

	
	
}
