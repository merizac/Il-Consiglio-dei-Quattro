package bonus;

import game.GameState;

public class BonusAzionePrincipale extends Bonus {

	int numBonus;
	

	/**
	 * @param numBonus
	 */
	public BonusAzionePrincipale(int numBonus) {
		super();
		this.numBonus = numBonus;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 107016793686196135L;

	@Override
	public void usaBonus(GameState gameState) {
		gameState.setBonusAzionePrincipale(true);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusAzionePrincipale";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numBonus;
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
		BonusAzionePrincipale other = (BonusAzionePrincipale) obj;
		if (numBonus != other.numBonus)
			return false;
		return true;
	}
	
	
}
