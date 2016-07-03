package server.model.market;

import common.gameDTO.MarketableDTO;
import server.model.game.Giocatore;

public interface Marketable {

	/**
	 * action for buy in market
	 * 
	 * @param acquirente
	 * @param offerta
	 * @return
	 */
	public boolean acquista(Giocatore acquirente, Offerta offerta);

	/**
	 * action for check if the player really own the objet that it'selling
	 * 
	 * @param venditore
	 * @return
	 */
	public boolean possiede(Giocatore venditore);

	/**
	 * marketable
	 * 
	 * @return
	 */
	public MarketableDTO instance();
}
