package game;

import java.io.Serializable;
import java.util.ArrayList;

import bonus.*;

public class PlanciaRe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6037414124896630885L;
	private final Balcone balconeRe;
	private final ArrayList<Bonus> bonusPremioRe;
	private final ArrayList<PunteggioNobiltà> percorsoNobiltà;
	/**
	 * @param balconeRe
	 * @param bonusPremioRe
	 * @param percorsoNobiltà
	 */
	public PlanciaRe(Balcone balconeRe, ArrayList<Bonus> bonusPremioRe,
			ArrayList<PunteggioNobiltà> percorsoNobiltà) {
		this.balconeRe = balconeRe;
		this.bonusPremioRe = bonusPremioRe;
		this.percorsoNobiltà = percorsoNobiltà;
	}
	/**
	 * @return the balconeRe
	 */
	public Balcone getBalconeRe() {
		return balconeRe;
	}
	/**
	 * @return the bonusPremioRe
	 */
	public ArrayList<Bonus> getBonusPremioRe() {
		return bonusPremioRe;
	}
	
	/**
	 * @return the percorsoNobiltà
	 */
	public ArrayList<PunteggioNobiltà> getPercorsoNobiltà() {
		return percorsoNobiltà;
	}
	
	
}
