package server.model.notify;

import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;

public interface Notify {
	
	public boolean daInviare(Giocatore giocatore);
	public ClientNotify notifyToClientNotify();
	
}
