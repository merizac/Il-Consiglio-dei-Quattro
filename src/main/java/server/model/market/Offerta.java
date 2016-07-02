package server.model.market;

import server.model.game.Giocatore;

public class Offerta {

	private final Giocatore venditore;
	private final Marketable marketable;
	private final int prezzo;

	public Offerta(Giocatore venditore, Marketable marketable, int prezzo) {
		if (venditore == null)
			throw new NullPointerException("Il giocatore venditore non deve essere null");
		else {
			this.venditore = venditore;
		}
		if (marketable == null)
			throw new NullPointerException("L'oggetto in vendita non deve essere null");
		else {
			this.marketable = marketable;
		}
		if (prezzo < 0) {
			throw new IllegalArgumentException("Il prezzo dell'oggetto in vendita deve essere non negativo");
		} else {
			this.prezzo = prezzo;
		}
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
