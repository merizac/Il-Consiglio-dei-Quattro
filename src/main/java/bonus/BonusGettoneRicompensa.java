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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cittàGiocatore == null) ? 0 : cittàGiocatore.hashCode());
		result = prime * result + numeroGettoni;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusGettoneRicompensa other = (BonusGettoneRicompensa) obj;
		if (cittàGiocatore == null) {
			if (other.cittàGiocatore != null)
				return false;
		} else if (!cittàGiocatore.equals(other.cittàGiocatore))
			return false;
		if (numeroGettoni != other.numeroGettoni)
			return false;
		return true;
	}
	
	
	
	

	
	
}
