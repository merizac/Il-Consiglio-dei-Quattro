package server.model.bonus;

import java.util.Arrays;

import server.model.game.GameState;
import server.model.notify.MessageNotify;

public class BonusMoneta extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6145370619214632336L;
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
	@Override
	public void usaBonus(GameState gameState) {
		gameState.getGiocatoreCorrente().aumentaRicchezza(monete);
		gameState.notifyObserver(new MessageNotify((monete==1) ? "Hai vinto "+monete+" moneta" :"Hai vinto"+ monete+" monete",
				Arrays.asList(gameState.getGiocatoreCorrente())));
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusMoneta [monete=" + monete + "]";
	}
	/**
	 * @return the monete
	 */
	public int getMonete() {
		return monete;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + monete;
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
		BonusMoneta other = (BonusMoneta) obj;
		if (monete != other.monete)
			return false;
		return true;
	}
	
	
}
