package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.List;
import bonus.Bonus;
import game.PlanciaRe;

public class PlanciaReDTO implements Serializable {

	private static final long serialVersionUID = -1277703422395979043L;

	private BalconeDTO balconeRe;
	private List<Bonus> bonusPremioRe;
	/**
	 * @return the balconeRe
	 */

	public BalconeDTO getBalconeRe() {
		return balconeRe;
	}
	/**
	 * @param balconeRe the balconeRe to set
	 */

	public void setBalconeRe(BalconeDTO balconeRe) {
		this.balconeRe = balconeRe;
	}
	/**
	 * @return the bonusPremioRe
	 */
	public List<Bonus> getBonusPremioRe() {
		return bonusPremioRe;
	}
	/**
	 * @param bonusPremioRe the bonusPremioRe to set
	 */
	public void setBonusPremioRe(List<Bonus> bonusPremioRe) {
		this.bonusPremioRe = bonusPremioRe;
	}

	
	public void inizializza(PlanciaRe planciaRe){
		this.balconeRe=new BalconeDTO();
		this.balconeRe.inizializza(planciaRe.getBalconeRe());
		this.setBonusPremioRe(planciaRe.getBonusPremioRe());
		
	}
	
	@Override
	public String toString() {
		return "PlanciaReDTO [balconeRe=" + balconeRe + ", bonusPremioRe=" + bonusPremioRe + "]";
	}
	
	
	

}
