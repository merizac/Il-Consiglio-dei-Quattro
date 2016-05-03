package game;

import java.util.ArrayList;
import java.util.HashSet;

import bonus.*;

public class CittàBonus extends Città {

	private final ArrayList<Bonus> bonus;
	
	

	/**
	 * @param nome
	 * @param regione
	 * @param colore
	 * @param empori
	 * @param cittàCollegate
	 */
	public CittàBonus(String nome, Regione regione, ColoreCittà colore, HashSet<Emporio> empori,
			ArrayList<Città> cittàCollegate, ArrayList<Bonus> bonus) {
		super(nome, regione, colore);
		this.bonus = bonus;
		this.colore.getCittà().add(this);
	}


	/**
	 * @return the bonus
	 */
	public ArrayList<Bonus> getBonus() {
		return bonus;
	}

	

	
}
