package mapping.gameToMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;

public class PlanciaReDTO implements Serializable {

	private static final long serialVersionUID = -1277703422395979043L;
	private Queue<ColoreDTO> balconeRe;
	private ArrayList<BonusDTO> bonusPremioRe;
	/**
	 * @return the balconeRe
	 */
	public Queue<ColoreDTO> getBalconeRe() {
		return balconeRe;
	}
	/**
	 * @param balconeRe the balconeRe to set
	 */
	public void setBalconeRe(Queue<ColoreDTO> balconeRe) {
		this.balconeRe = balconeRe;
	}
	/**
	 * @return the bonusPremioRe
	 */
	public ArrayList<BonusDTO> getBonusPremioRe() {
		return bonusPremioRe;
	}
	/**
	 * @param bonusPremioRe the bonusPremioRe to set
	 */
	public void setBonusPremioRe(ArrayList<BonusDTO> bonusPremioRe) {
		this.bonusPremioRe = bonusPremioRe;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlanciaReDTO [balconeRe=" + balconeRe + ", bonusPremioRe=" + bonusPremioRe + "]";
	}
	
	
	

}
