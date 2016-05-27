package game;

import java.util.ArrayList;

import bonus.*;

public class CittàBonus extends Città {

	private transient ArrayList<Bonus> bonus;
	

	/**
	 * @param nome
	 * @param regione
	 * @param colore
	 * @param empori
	 * @param cittàCollegate
	 */
	public CittàBonus(String nome, Regione regione, ColoreCittà colore, ArrayList<Bonus> bonus) {
		super(nome, regione, colore);
		this.bonus = bonus;
		colore.getCittà().add(this);
	}


	/**
	 * @return the bonus
	 */
	public ArrayList<Bonus> getBonus() {
		return bonus;
	}
	
	public void setBonus(ArrayList<Bonus> bonus) {
		this.bonus = bonus;
	}
	
	

	

	
}
