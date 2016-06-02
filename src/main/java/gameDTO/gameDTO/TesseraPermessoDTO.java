package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import bonus.Bonus;
import game.Città;
import game.Giocatore;
import game.TesseraPermesso;
import game.market.Marketable;
import gameDTO.azioniDTO.ControlloParametri;

public class TesseraPermessoDTO implements Serializable, MarketableDTO  {

	private static final long serialVersionUID = 251246647055835184L;
	private Set<CittàDTO> città;
	private ArrayList<Bonus> bonus;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TesseraPermesso  città:" + città + ", bonus:" + bonus;
	}
	public void inizializza(TesseraPermesso t) {
		this.città=new HashSet<>();
		for(Città c: t.getCittà()){
			CittàDTO cittàDTO=new CittàDTO();
			cittàDTO.inizializza(c);
			città.add(cittàDTO);
		}
		this.setBonus(t.getBonus());
	}
	@Override
	public Marketable creaMarketable(Giocatore giocatore) {
		return ControlloParametri.cercaTesseraPermesso(this, giocatore.getTesserePermesso());
	}
	
	

}
