package game;

import game.market.Marketable;
import game.market.Offerta;

public final class CartaPolitica implements Marketable {

	private final Colore colore;
	
	/**
	 * @param colore
	 */
	public CartaPolitica(Colore colore) {
		this.colore = colore;
	}


	/**
	 * @return the colore
	 */
	public Colore getColore() {
		return colore;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CartaPolitica [colore=" + colore + "]";
	}


	@Override
	public boolean acquista(Giocatore acquirente, Offerta offerta) {
		if(!acquirente.diminuisciRicchezza(offerta.getPrezzo()))
			return false;
		else{
			acquirente.aggiungiCartaPolitica(this);
			offerta.getVenditore().rimuoviCartaPolitica(this);
			offerta.getVenditore().aumentaRicchezza(offerta.getPrezzo());
			return true;
		}
	}


	@Override
	public boolean possiede(Giocatore venditore) {
		if(!venditore.getCartePolitica().contains(this))
			return false;
		else
			return true;
	}
	
	


}
