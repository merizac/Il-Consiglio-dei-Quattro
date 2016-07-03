package common.gameDTO;

import java.io.Serializable;
import java.util.List;

import server.model.bonus.Bonus;
import server.model.game.PlanciaRe;

public class PlanciaReDTO implements Serializable {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -1277703422395979043L;

	/**
	 * king's balcony
	 */
	private BalconeDTO balconeRe;
	/**
	 * king's bonus
	 */
	private List<Bonus> bonusPremioRe;

	/**
	 * @return the balconeRe
	 */

	public BalconeDTO getBalconeRe() {
		return balconeRe;
	}

	/**
	 * @param balconeRe
	 *            the balconeRe to set
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
	 * @param bonusPremioRe
	 *            the bonusPremioRe to set
	 */
	public void setBonusPremioRe(List<Bonus> bonusPremioRe) {
		this.bonusPremioRe = bonusPremioRe;
	}

	/**
	 * plancia re inizialisation
	 * 
	 * @param planciaRe
	 */
	public void inizializza(PlanciaRe planciaRe) {
		this.balconeRe = new BalconeDTO();
		this.balconeRe.inizializza(planciaRe.getBalconeRe());
		this.setBonusPremioRe(planciaRe.getBonusPremioRe());

	}

	/**
	 * to string
	 */
	@Override
	public String toString() {
		return "PlanciaReDTO [balconeRe=" + balconeRe + ", bonusPremioRe=" + bonusPremioRe + "]";
	}

}
