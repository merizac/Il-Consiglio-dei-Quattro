package game.market;

import game.Giocatore;

public interface Marketable {
	public boolean acquista(Giocatore acquirente,Offerta offerta);
	public boolean possiede(Giocatore venditore);
}
