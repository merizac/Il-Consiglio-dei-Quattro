package gameDTO.gameDTO;

import java.io.Serializable;

import game.CartaPolitica;
import game.Colore;
import game.Giocatore;
import game.market.Marketable;

public class CartaPoliticaDTO implements Serializable, MarketableDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6234645852702284026L;
	private String colore;

	/**
	 * @return the colore
	 */
	public String getColore() {
		return colore;
	}

	/**
	 * @param colore the colore to set
	 */
	public void setColore(String colore) {
		this.colore = colore;
	}
	
	public void inizializza(CartaPolitica cartaPolitica){
		this.setColore(cartaPolitica.getColore().getColore());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return colore;
	}

	@Override
	public Marketable creaMarketable(Giocatore giocatore) {
		return new CartaPolitica(new Colore(this.colore));
	}
	
	
	
}
