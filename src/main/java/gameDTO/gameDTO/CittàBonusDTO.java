package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;

import bonus.Bonus;

public class CittàBonusDTO extends CittàDTO implements Serializable{

	private static final long serialVersionUID = 6054052021956866160L;
	private ArrayList<Bonus> bonus;
	/**
	 * @return the bonus
	 */
	public ArrayList<Bonus> getBonus() {
		return bonus;
	}
	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(ArrayList<Bonus> bonus) {
		this.bonus = bonus;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CittàBonusDTO [bonus=" + bonus + "]";
	}

}
