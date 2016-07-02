package server.model.notify;

import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;

public interface Notify {
	
	/**
	 * notified players
	 * 
	 * @param giocatore
	 * @return
	 */
	public boolean daInviare(Giocatore giocatore);

	/**
	 * create client notify
	 * 
	 * @return CLientNotify
	 */
	public ClientNotify notifyToClientNotify();

}
