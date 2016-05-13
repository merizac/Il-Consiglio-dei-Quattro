package bonus;

import game.GameState;

public class BonusAzionePrincipale extends Bonus {


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
