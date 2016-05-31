package game.notify;

import game.Giocatore;
import view.clientNotify.ClientNotify;

public interface Notify {
	
	public boolean daInviare(Giocatore giocatore);
	public ClientNotify notifyToClientNotify();
	
}
