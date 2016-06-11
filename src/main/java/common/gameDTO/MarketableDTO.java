package common.gameDTO;

import java.io.Serializable;

import server.model.game.Giocatore;
import server.model.market.Marketable;
import utility.ParameterException;

public interface MarketableDTO extends Serializable {
	
	public Marketable creaMarketable(Giocatore giocatore) throws ParameterException;
	public void creaMarketableDTO(Marketable marketable);
}
