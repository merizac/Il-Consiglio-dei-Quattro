package server.model.notify;

import java.util.List;
import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.MarketClientNotify;

public class MarketNotify implements Notify {

	private List<Giocatore> giocatori;
	private boolean fineMarket;

	/**
	 * notify when market end
	 * 
	 * @param giocatori
	 * @param fineMarket
	 */
	public MarketNotify(List<Giocatore> giocatori, boolean fineMarket) {
		this.giocatori = giocatori;
		this.fineMarket = fineMarket;
	}

	/**
	 * notified players
	 */
	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	/**
	 * create client notify
	 */
	@Override
	public ClientNotify notifyToClientNotify() {
		return new MarketClientNotify(fineMarket);
	}

}
