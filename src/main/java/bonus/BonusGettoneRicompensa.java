package bonus;


import java.util.ArrayList;
import game.CittàBonus;
import game.GameState;

public class BonusGettoneRicompensa extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7078597933216059193L;
	private final int numeroGettoni;
	private ArrayList<CittàBonus> cittàGiocatore;
	
	public BonusGettoneRicompensa(int numeroGettoni) {
		super();
		this.numeroGettoni=numeroGettoni;
	}

	@Override
	public void usaBonus(GameState gameState) {		
		/*PassaggioParametri passaggioParametri = new PassaggioParametri(gameState);
		if(numeroGettoni==1)
			cittàGiocatore.add((CittàBonus)passaggioParametri.scegliCittàEmporio());
		else
			return;*/
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusGettoneRicompensa [numeroGettoni=" + numeroGettoni + "]";
	}
	
	
	
	

	
	
}
