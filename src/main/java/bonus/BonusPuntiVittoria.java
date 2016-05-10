package bonus;

import game.GameState;

public class BonusPuntiVittoria extends Bonus {

	private int puntiVittoria;

	/**
	 * @param gameState
	 */
	public BonusPuntiVittoria(int puntiVittoria) {
		super();
		this.puntiVittoria=puntiVittoria;	
	}
	/**
	 * add puntiVittoria to variable punteggioVittoria of Giocatore
	 */
	public void usaBonus(GameState gameState) {
		gameState.getGiocatoreCorrente().aumentaPuntiVittoria(puntiVittoria);
	}
}
