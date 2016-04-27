package bonus;

import game.Partita;

public class BonusAiutanti extends Bonus {

	private int aiutanti;
	/**
	 * 
	 * @param aiutanti
	 * @param partita
	 */
	public BonusAiutanti(int aiutanti, Partita partita){
		super(partita);
		this.aiutanti = aiutanti;
	}
	/**
	 * add aiutanti to the variable aiutanti of Giocatore
	 */
	public void usaBonus() {
		this.getPartita().getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(this.aiutanti);
	}
}
