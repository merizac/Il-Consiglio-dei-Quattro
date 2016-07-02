package server.model.notify;

import java.util.List;

import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.StartGiocatoreClientNotify;

public class StartGiocatoreNotify implements Notify {

	private List<Giocatore> giocatori;

	/**
	 * start game player notify
	 * 
	 * @param giocatori
	 */
	public StartGiocatoreNotify(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
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
		return new StartGiocatoreClientNotify();
	}

}
