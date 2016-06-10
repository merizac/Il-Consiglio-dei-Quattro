package common.gameDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.azioniDTO.ControlloParametri;
import server.model.bonus.Bonus;
import server.model.game.Città;
import server.model.game.Giocatore;
import server.model.game.TesseraPermesso;
import server.model.market.Marketable;
import utility.ParameterException;

public class TesseraPermessoDTO implements Serializable, MarketableDTO {

	private static final long serialVersionUID = 251246647055835184L;
	private Set<CittàDTO> città=new HashSet<>();
	private List<Bonus> bonus;
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
	public List<Bonus> getBonus() {
		return bonus;
	}
	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(List<Bonus> bonus) {
		this.bonus = bonus;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String città=" [";
		for(CittàDTO c: this.città){
			città=città+c.getNome()+" ";
		}
		città=città+"]";
		return "TesseraPermesso  città:" + città + ", bonus:" + bonus;
	}
	
	
	public void inizializza(TesseraPermesso t) {
		
		for(Città c: t.getCittà()){
			CittàDTO cittàDTO=new CittàDTO();
			cittàDTO.inizializza(c);
			città.add(cittàDTO);
		}
		this.setBonus(t.getBonus());
	}
	
	@Override
	public TesseraPermesso creaMarketable(Giocatore giocatore) throws ParameterException {
		return ControlloParametri.cercaTesseraPermesso(this, giocatore.getTesserePermesso());
	}
	
	@Override
	public void creaMarketableDTO(Marketable marketable) {
		TesseraPermesso t=(TesseraPermesso)marketable;
		this.inizializza(t);
		
	}


	

}
