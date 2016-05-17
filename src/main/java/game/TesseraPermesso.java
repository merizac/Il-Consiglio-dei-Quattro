package game;

import java.util.ArrayList;

import bonus.*;
import game.market.Marketable;
import game.market.Offerta;

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
		return "TesseraPermesso [città=" + città + ", bonus=" + bonus + ", regione=" + regione + "]";
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


}
