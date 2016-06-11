package server.model.game;

import java.util.ArrayList;

import common.gameDTO.MarketableDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.bonus.*;
import server.model.market.Marketable;
import server.model.market.Offerta;

public final class TesseraPermesso implements Marketable {

	private final ArrayList<Città> città;
	private final ArrayList<Bonus> bonus;
	private final Regione regione;


	/**
	 * @param città
	 * @param bonus
	 * @param regione
	 */
	public TesseraPermesso(ArrayList<Città> città, ArrayList<Bonus> bonus, Regione regione){
		this.città = città;
		this.bonus = bonus;
		this.regione = regione;
		regione.getMazzoTesserePermesso().getCarte().add(this);
	}


	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}
	
	/**
	 * @return the città
	 */
	public ArrayList<Città> getCittà() {
		return città;
	}


	/**
	 * @return the bonus
	 */
	public ArrayList<Bonus> getBonus() {
		return bonus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TesseraPermesso [ (" + città + "), (" + bonus + ") ]";
	}


	@Override
	public boolean acquista(Giocatore acquirente, Offerta offerta) {
		if(!acquirente.diminuisciRicchezza(offerta.getPrezzo()))
			return false;
		else{
			acquirente.aggiungiTesseraPermesso(this);
			offerta.getVenditore().rimuoviTesseraPermesso(this);
			offerta.getVenditore().aumentaRicchezza(offerta.getPrezzo());
			return true;
		}
	}


	@Override
	public boolean possiede(Giocatore venditore) {
		if(!venditore.getTesserePermesso().contains(this))
			return false;
		else
			return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonus == null) ? 0 : bonus.hashCode());
		result = prime * result + ((città == null) ? 0 : città.hashCode());
		result = prime * result + ((regione == null) ? 0 : regione.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TesseraPermesso other = (TesseraPermesso) obj;
		if (bonus == null) {
			if (other.bonus != null)
				return false;
		} else if (!bonus.equals(other.bonus))
			return false;
		if (città == null) {
			if (other.città != null)
				return false;
		} else if (!città.equals(other.città))
			return false;
		if (regione == null) {
			if (other.regione != null)
				return false;
		} else if (!regione.equals(other.regione))
			return false;
		return true;
	}


	@Override
	public MarketableDTO instance() {
		return new TesseraPermessoDTO();
	}
	
	


}
