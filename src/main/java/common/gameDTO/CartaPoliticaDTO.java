package common.gameDTO;

import java.io.Serializable;

import server.model.game.CartaPolitica;
import server.model.game.Colore;
import server.model.game.Giocatore;
import server.model.market.Marketable;

public class CartaPoliticaDTO implements Serializable, MarketableDTO {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 6234645852702284026L;
	/**
	 * color
	 */
	private String colore;

	/**
	 * @return the colore
	 */
	public String getColore() {
		return colore;
	}

	/**
	 * @param colore
	 *            the colore to set
	 */
	public void setColore(String colore) {
		this.colore = colore;
	}

	/**
	 * create an object CartaPolitica from this
	 */
	@Override
	public CartaPolitica creaMarketable(Giocatore giocatore) {
		return new CartaPolitica(new Colore(this.colore));
	}

	/**
	 * create an object marketableDTO from a marketable
	 */
	@Override
	public void creaMarketableDTO(Marketable marketable) {
		CartaPolitica cartaPolitica = (CartaPolitica) marketable;
		this.inizializza(cartaPolitica);

	}

	/**
	 * map cartaPolitica carta to cartaPoliticaDTO
	 * 
	 * @param carta
	 */
	public void inizializza(CartaPolitica carta) {
		this.setColore(carta.getColore().getColore());
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return colore;
	}
}
