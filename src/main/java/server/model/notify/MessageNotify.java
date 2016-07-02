package server.model.notify;

import java.util.List;

import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.MessageClientNotify;

public class MessageNotify implements Notify {

	private String messaggio;
	private boolean market;
	private List<Giocatore> giocatori;

	/**
	 * notify message to the player
	 * 
	 * @param messaggio
	 * @param giocatori
	 * @param market
	 */
	public MessageNotify(String messaggio, List<Giocatore> giocatori, boolean market) {
		this.messaggio = messaggio;
		this.giocatori = giocatori;
		this.market=market;
	}

	/**
	 * notified players
	 */
	@Override
	public boolean daInviare(Giocatore giocatore) {
		if (giocatore == null)
			throw new NullPointerException("Il giocatore non può essere null");
		else
			return giocatori.contains(giocatore);
	}

	/**
	 * create client notify
	 */
	@Override
	public ClientNotify notifyToClientNotify() {
		return new MessageClientNotify(messaggio, market);
	}

}
