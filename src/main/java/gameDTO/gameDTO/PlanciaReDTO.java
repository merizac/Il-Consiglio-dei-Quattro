package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import bonus.Bonus;
import game.Consigliere;
import game.PlanciaRe;

public class PlanciaReDTO implements Serializable {

	private static final long serialVersionUID = -1277703422395979043L;
	private ArrayList<ConsigliereDTO> balconeRe;
	private ArrayList<Bonus> bonusPremioRe;
	/**
	 * @return the balconeRe
	 */
	public ArrayList<ConsigliereDTO> getBalconeRe() {
		return balconeRe;
	}
	/**
	 * @param balconeRe the balconeRe to set
	 */
	public void setBalconeRe(ArrayList<ConsigliereDTO> balconeRe) {
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public void inizializza(PlanciaRe planciaRe){
		this.balconeRe = new ArrayList<>();
		for(Consigliere c: planciaRe.getBalconeRe().getConsigliere()){
			ConsigliereDTO consigliereDTO = new ConsigliereDTO();
			consigliereDTO.inizializza(c);
			balconeRe.add(consigliereDTO);
		}
		this.setBonusPremioRe(planciaRe.getBonusPremioRe());
		
	}
	
	@Override
	public String toString() {
		return "PlanciaReDTO [balconeRe=" + balconeRe + ", bonusPremioRe=" + bonusPremioRe + "]";
	}
	
	
	

}
