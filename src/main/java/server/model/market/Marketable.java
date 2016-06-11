package server.model.market;

import common.gameDTO.MarketableDTO;
import server.model.game.Giocatore;

public interface Marketable {
	public boolean acquista(Giocatore acquirente,Offerta offerta);
	public boolean possiede(Giocatore venditore);
	public MarketableDTO instance();
}
