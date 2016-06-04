package bonus;

import game.GameState;

public abstract class BonusNobiltà extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 953130507727170967L;	
	
	@Override
	public void usaBonus(GameState gameState){
		gameState.getGiocatoreCorrente().getBonusNobiltà().add(this);
	}

}
