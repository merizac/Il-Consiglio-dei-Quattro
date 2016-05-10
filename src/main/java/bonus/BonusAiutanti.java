package bonus;

import game.GameState;

public class BonusAiutanti extends Bonus {

	private int aiutanti;
	/**
	 * 
	 * @param aiutanti
	 * @param partita
	 */
	public BonusAiutanti(int aiutanti){
		super();
		this.aiutanti = aiutanti;
	}
	/**
	 * add aiutanti to the variable aiutanti of Giocatore
	 */
	public void usaBonus(GameState gameState) {
		gameState.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(this.aiutanti);
	}
}
