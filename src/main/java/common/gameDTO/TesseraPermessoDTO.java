package common.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.azioniDTO.ControlloParametri;
import server.model.bonus.Bonus;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Giocatore;
import server.model.game.TesseraPermesso;
import server.model.market.Marketable;
import utility.ParameterException;

public class TesseraPermessoDTO implements Serializable, MarketableDTO {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 251246647055835184L;
	/**
	 * city of permit tile
	 */
	private Set<CittàDTO> città = new HashSet<>();
	/**
	 * bonus in permit tile
	 */
	private List<Bonus> bonus;

	/**
	 * @return the città
	 */
	public Set<CittàDTO> getCittà() {
		return città;
	}

	/**
	 * @param città
	 *            the città to set
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
	 * @param bonus
	 *            the bonus to set
	 */
	public void setBonus(List<Bonus> bonus) {
		this.bonus = bonus;
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		ordinaCittà();
		String cittàToString = " [";
		for (CittàDTO c : this.città) {
			cittàToString = cittàToString + c.getNome() + " ";
		}
		cittàToString = cittàToString + "]";
		return "TesseraPermesso  città:" + ordinaCittà() + ", bonus:" + bonus;
	}

	/**
	 * permit tile inizitialisation
	 * 
	 * @param t
	 */
	public void inizializza(TesseraPermesso t) {

		for (Città c : t.getCittà()) {
			if (c instanceof CittàBonus) {
				CittàBonusDTO cittàDTO = new CittàBonusDTO();
				((CittàBonusDTO) cittàDTO).inizializza((CittàBonus) c);
				città.add(cittàDTO);
			} else {
				CittàDTO cittàDTO = new CittàDTO();
				cittàDTO.inizializza(c);
				città.add(cittàDTO);
			}
		}
		this.setBonus(t.getBonus());
	}

	/**
	 * create marketable object
	 */
	@Override
	public TesseraPermesso creaMarketable(Giocatore giocatore) throws ParameterException {
		return ControlloParametri.cercaTesseraPermesso(this, giocatore.getTesserePermesso());
	}

	/**
	 * initializing dto marketable
	 */
	@Override
	public void creaMarketableDTO(Marketable marketable) {
		TesseraPermesso t = (TesseraPermesso) marketable;
		this.inizializza(t);

	}

	/**
	 * order city by name
	 */
	public List<String> ordinaCittà() {
		List<String> nomiCittà = new ArrayList<>();
		for (CittàDTO c : città) {
			nomiCittà.add(c.getNome());
		}

		Collections.sort(nomiCittà);
		return nomiCittà;
	}

}
