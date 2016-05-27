package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import bonus.Bonus;
import game.Città;
import game.TesseraPermesso;

public class TesseraPermessoDTO implements Serializable  {

	private static final long serialVersionUID = 251246647055835184L;
	private Set<CittàDTO> città;
	private ArrayList<Bonus> bonus;
	private RegioneDTO regione;
	/**
	 * @return the città
	 */
	public Set<CittàDTO> getCittà() {
		return città;
	}
	/**
	 * @param città the città to set
	 */
	public void setCittà(Set<CittàDTO> città) {
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
	public void inizializza(TesseraPermesso t) {
		RegioneDTO regioneDTO =new RegioneDTO();
		regioneDTO.inizializza(t.getRegione());
		this.setRegione(regioneDTO);
		this.città=new HashSet<>();
		for(Città c: t.getCittà()){
			CittàDTO cittàDTO=new CittàDTO();
			cittàDTO.inizializza(c);
			città.add(cittàDTO);
		}
		this.setBonus(t.getBonus());
	}
	
	

}
