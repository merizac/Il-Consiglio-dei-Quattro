package bonus;

import game.CartaPolitica;
import game.Mazzo;
import game.Partita;

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
	public void usaBonus(Partita partita) {
		Mazzo<CartaPolitica> mazzo = partita.getTabellone().getMazzoCartePolitica();
		for(int numCarte=0; numCarte<cartePolitica; numCarte++){
			partita.getGiocatoreCorrente().aggiungiCartaPolitica(mazzo.pescaCarte());
		}
		
	}
}
