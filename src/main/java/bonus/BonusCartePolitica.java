package bonus;

import game.CartaPolitica;
import game.GameState;
import game.Mazzo;

public class BonusCartePolitica extends Bonus {

	private final int cartePolitica;
	
	public BonusCartePolitica(int cartePolitica) {
		super();
		this.cartePolitica=cartePolitica;
	}
	/**
	 * add politic cards to the player 
	 */
	@Override
	public void usaBonus(GameState gameState) {
		Mazzo<CartaPolitica> mazzo = gameState.getMazzoCartePolitica();
		for(int numCarte=0; numCarte<cartePolitica; numCarte++){
			gameState.getGiocatoreCorrente().aggiungiCartaPolitica(mazzo.pescaCarte());
		}
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusCartePolitica [cartePolitica=" + cartePolitica + "]";
	}
	
	
}
