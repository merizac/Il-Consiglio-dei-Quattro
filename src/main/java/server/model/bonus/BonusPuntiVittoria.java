package server.model.bonus;

import java.util.Arrays;

import server.model.game.GameState;
import server.model.notify.MessageNotify;

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
		if(puntiVittoria<=0)
			throw new IllegalArgumentException("Bonus punti vittoria deve ricevere un valore positivo non nullo");
		this.puntiVittoria=puntiVittoria;	
	}
	
	/**
	 * add puntiVittoria to variable punteggioVittoria of Giocatore
	 */
	
	@Override
	public void usaBonus(GameState gameState) {
		gameState.getGiocatoreCorrente().aumentaPuntiVittoria(puntiVittoria);
		gameState.notifyObserver(new MessageNotify((puntiVittoria==1) ? "Hai vinto "+puntiVittoria+" punto vittoria\n" 
				:"Hai vinto"+ puntiVittoria+" punti vittoria\n", Arrays.asList(gameState.getGiocatoreCorrente())));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return "BonusPuntiVittoria " + puntiVittoria;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + puntiVittoria;
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
		BonusPuntiVittoria other = (BonusPuntiVittoria) obj;
		if (puntiVittoria != other.puntiVittoria)
			return false;
		return true;
	}

	/**
	 * @return the puntiVittoria
	 */
	public int getPuntiVittoria() {
		return puntiVittoria;
	}
	
	
	
}
