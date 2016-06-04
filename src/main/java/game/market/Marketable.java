package game.market;

import game.Giocatore;
import gameDTO.gameDTO.MarketableDTO;

public interface Marketable {
	public boolean acquista(Giocatore acquirente,Offerta offerta);
	public boolean possiede(Giocatore venditore);
	public MarketableDTO instance();
}
