package server.model.game;

import java.util.List;

import server.model.bonus.*;

public class CittàBonus extends Città {

	private List<Bonus> bonus;
	

	/**
	 * @param nome
	 * @param regione
	 * @param colore
	 * @param empori
	 * @param cittàCollegate
	 */
	public CittàBonus(String nome, Regione regione, ColoreCittà colore, List<Bonus> bonus) {
		super(nome, regione, colore);
		if(bonus==null)
			throw new NullPointerException("La città bonus deve avere dei bonus");
		this.bonus = bonus;
		colore.getCittà().add(this);
	}


	/**
	 * @return the bonus
	 */
	public List<Bonus> getBonus() {
		return bonus;
	}
	
	public void setBonus(List<Bonus> bonus) {
		this.bonus = bonus;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CittàBonus [bonus=" + bonus + "]";
	}
	
}
