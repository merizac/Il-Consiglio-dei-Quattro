package server.model.bonus;

import java.util.Arrays;

import server.model.game.GameState;
import server.model.notify.MessageNotify;

public class BonusAiutanti extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5026851552875939482L;
	private int aiutanti;
	/**
	 * costruttore
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
	@Override
	public void usaBonus(GameState gameState) {
		gameState.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(this.aiutanti);
		gameState.notifyObserver(new MessageNotify((aiutanti==1) ?"Hai vinto "+aiutanti+" aiutante" : "Hai vinto "+aiutanti+" aiutanti"
				, Arrays.asList(gameState.getGiocatoreCorrente())));
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusAiutanti " + aiutanti;
	}
	/**
	 * @return the aiutanti
	 */
	public int getAiutanti() {
		return aiutanti;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aiutanti;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusAiutanti other = (BonusAiutanti) obj;
		if (aiutanti != other.aiutanti)
			return false;
		return true;
	}
	
	
}
