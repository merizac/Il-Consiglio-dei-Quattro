package game;

import java.util.ArrayList;

import bonus.*;

public final class TesseraPermesso {

	private final ArrayList<Città> città;
	private final ArrayList<Bonus> bonus;
	private final String regione;


	/**
	 * @param città
	 * @param bonus
	 * @param regione
	 */
	public TesseraPermesso(ArrayList<Città> città, ArrayList<Bonus> bonus, String regione) {
		super();
		this.città = città;
		this.bonus = bonus;
		this.regione = regione;
	}


	/**
	 * @return the città
	 */
	public ArrayList<Città> getCittà() {
		return città;
	}


	/**
	 * @return the bonus
	 */
	public ArrayList<Bonus> getBonus() {
		return bonus;
	}


	/**
	 * @return the regione
	 */
	public String getRegione() {
		return regione;
	}

}
