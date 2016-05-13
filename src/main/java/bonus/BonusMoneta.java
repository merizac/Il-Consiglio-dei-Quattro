package bonus;

import game.GameState;

public class BonusMoneta extends Bonus {

	private int monete;
	/**
	 * constructor of BonusMoneta
	 * @param monete
	 * @param gameState
	 */
	public BonusMoneta(int monete){
		super();
		this.monete=monete;
	}
	/**
	 * add monete to variable punteggioRicchezza of Giocatore
	 */
	public void usaBonus(GameState gameState) {
		gameState.getGiocatoreCorrente().aumentaRicchezza(monete);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusMoneta [monete=" + monete + "]";
	}
	
	
}
