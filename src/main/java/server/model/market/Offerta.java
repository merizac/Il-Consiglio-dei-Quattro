package server.model.market;

import server.model.game.Giocatore;

public class Offerta {
	
	private final Giocatore venditore;
	private final Marketable marketable;
	private final int prezzo;
	
	public Offerta(Giocatore venditore, Marketable marketable, int prezzo){
		this.venditore=venditore;
		this.marketable=marketable;
		this.prezzo=prezzo;
	}

	/**
	 * @return the venditore
	 */
	public Giocatore getVenditore() {
		return venditore;
	}

	/**
	 * @return the marketable
	 */
	public Marketable getMarketable() {
		return marketable;
	}

	/**
	 * @return the prezzo
	 */
	public int getPrezzo() {
		return prezzo;
	}
	

}
