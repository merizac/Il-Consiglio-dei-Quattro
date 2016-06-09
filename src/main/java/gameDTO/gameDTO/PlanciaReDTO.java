package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import bonus.Bonus;
import game.Consigliere;
import game.PlanciaRe;
import gameDTO.BalconeDTO;

public class PlanciaReDTO implements Serializable {

	private static final long serialVersionUID = -1277703422395979043L;
	private BalconeDTO balconeRe;
	private ArrayList<Bonus> bonusPremioRe;
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
	public ArrayList<Bonus> getBonusPremioRe() {
		return bonusPremioRe;
	}
	/**
	 * @param bonusPremioRe the bonusPremioRe to set
	 */
	public void setBonusPremioRe(ArrayList<Bonus> bonusPremioRe) {
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
