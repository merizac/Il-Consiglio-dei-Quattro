package common.gameDTO;

import java.io.Serializable;

import server.model.bonus.Bonus;
import server.model.game.ColoreCittà;

public class ColoreBonusDTO extends ColoreDTO implements Serializable {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 3272694886315865223L;
	/**
	 * bonus of color tiles
	 */
	private Bonus bonus;

	/**
	 * @return the bonusDTO
	 */
	public Bonus getBonusDTO() {
		return bonus;
	}

	/**
	 * @param bonusDTO
	 *            the bonusDTO to set
	 */
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}

	/**
	 * map a coloreCittà into a ColoreBonusDTO
	 * 
	 * @param coloreCittà
	 */
	public void inizializza(ColoreCittà coloreCittà) {
		this.setColore(coloreCittà.getColore());
		this.setBonus(coloreCittà.getBonusColore());
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "ColoreBonus [bonusDTO=" + bonus + "]";
	}

}
