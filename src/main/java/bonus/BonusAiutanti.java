package bonus;

import game.GameState;

public class BonusAiutanti extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5026851552875939482L;
	private int aiutanti;
	/**
	 * 
	 * @param aiutanti
	 * @param partita
	 */
	public BonusAiutanti(int aiutanti){
		if(aiutanti<0) 
				throw new IllegalArgumentException("Il numero di aiutanti deve essere positivo");
		else	
			this.aiutanti = aiutanti;
	}
	/**
	 * add aiutanti to the variable aiutanti of Giocatore
	 */
	public void usaBonus(GameState gameState) {
		gameState.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(this.aiutanti);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusAiutanti [aiutanti=" + aiutanti + "]";
	}
	/**
	 * @return the aiutanti
	 */
	public int getAiutanti() {
		return aiutanti;
	}
	
	
}
