package common.gameDTO;

import java.io.Serializable;

import server.model.bonus.Bonus;
import server.model.game.ColoreCittà;

public class ColoreBonusDTO extends ColoreDTO implements Serializable {

	private static final long serialVersionUID = 3272694886315865223L;
	private Bonus bonus;
	/**
	 * @return the bonusDTO
	 */
	public Bonus getBonusDTO() {
		return bonus;
	}
	/**
	 * @param bonusDTO the bonusDTO to set
	 */
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
	public void inizializza(ColoreCittà coloreCittà){
		this.setColore(coloreCittà.getColore());
		this.setBonus(coloreCittà.getBonusColore());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ColoreBonus [bonusDTO=" + bonus + "]";
	}
	
	

}
