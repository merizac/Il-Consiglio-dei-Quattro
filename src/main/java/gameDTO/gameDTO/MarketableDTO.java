package gameDTO.gameDTO;

import java.io.Serializable;

import game.Giocatore;
import game.market.Marketable;

public interface MarketableDTO extends Serializable {
	
	public Marketable creaMarketable(Giocatore giocatore);

}
