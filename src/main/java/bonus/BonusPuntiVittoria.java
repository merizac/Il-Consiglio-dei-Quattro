package bonus;

import game.GameState;

public class BonusPuntiVittoria extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5468530680642958233L;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusPuntiVittoria [puntiVittoria=" + puntiVittoria + "]";
	}
	
	
}
