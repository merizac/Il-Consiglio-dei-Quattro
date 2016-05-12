package game;

import java.util.ArrayList;

import bonus.*;

public final class TesseraPermesso {

	private final ArrayList<Città> città;
	private final ArrayList<Bonus> bonus;
	private final Regione regione;


	/**
	 * @param città
	 * @param bonus
	 * @param regione
	 */
	public TesseraPermesso(ArrayList<Città> città, ArrayList<Bonus> bonus, Regione regione){
		this.città = città;
		this.bonus = bonus;
		this.regione = regione;
		System.out.println(regione.toString());
		regione.getMazzoTesserePermesso().getCarte().add(this);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TesseraPermesso [città=" + città + ", bonus=" + bonus + ", regione=" + regione + "]";
	}


}
