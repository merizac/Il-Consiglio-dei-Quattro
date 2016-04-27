package game;

import java.util.ArrayList;

import bonus.*;

public final class PunteggioNobiltà {

	private final int puntiNobiltà;
	private final ArrayList<Bonus> bonus;
	
	
	/**
	 * @param puntiNobiltà
	 * @param bonus
	 */
	public PunteggioNobiltà(int puntiNobiltà, ArrayList<Bonus> bonus) {
		this.puntiNobiltà = puntiNobiltà;
		this.bonus = bonus;
	}

	/**
	 * get bonus
	 * @return ArrayList<Bonus>
	 */
	public ArrayList<Bonus> getBonus() {
		return this.bonus;
	}

	/**
	 * get puntiNobiltà
	 * @return puntiNobiltà
	 */
	public int getPuntiNobiltà() {
		return this.puntiNobiltà;
	}
	
	/*public void aggiungiPuntiNobiltà(int puntiNobiltà){
		this.puntiNobiltà=this.puntiNobiltà + puntiNobiltà;
		if (bonus.isEmpty()) {
			for(Bonus bonus: bonus){
				bonus.usaBonus();
			}
		}
	}*/
}