package bonus;

import game.GameState;

public class BonusAzionePrincipale extends Bonus {


	/**
	 * 
	 */
	private static final long serialVersionUID = 107016793686196135L;

	public BonusAzionePrincipale() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	
	
}
