package mapping.gameToMap;

import java.io.Serializable;
import java.util.ArrayList;

import bonus.Bonus;

public class TesseraPermessoDTO implements Serializable  {

	private static final long serialVersionUID = 251246647055835184L;
	private ArrayList<CittàDTO> città;
	private ArrayList<Bonus> bonus;
	private RegioneDTO regione;
	/**
	 * @return the città
	 */
	public ArrayList<CittàDTO> getCittà() {
		return città;
	}
	/**
	 * @param città the città to set
	 */
	public void setCittà(ArrayList<CittàDTO> città) {
		this.città = città;
	}
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
	/**
	 * @return the regione
	 */
	public RegioneDTO getRegione() {
		return regione;
	}
	/**
	 * @param regione the regione to set
	 */
	public void setRegione(RegioneDTO regione) {
		this.regione = regione;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TesseraPermessoDTO [città=" + città + ", bonus=" + bonus + ", regione=" + regione + "]";
	}
	
	

}
