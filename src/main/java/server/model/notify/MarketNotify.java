package server.model.notify;

import java.util.List;
import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.MarketClientNotify;

public class MarketNotify implements Notify {
	
	private List<Giocatore> giocatori;
	private boolean fineMarket;

	public MarketNotify(List<Giocatore> giocatori, boolean fineMarket) {
		this.giocatori=giocatori;
		this.fineMarket=fineMarket;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	@Override
	public ClientNotify notifyToClientNotify() {		
		return new MarketClientNotify(fineMarket);
	}

}
