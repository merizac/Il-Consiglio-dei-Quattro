package server.model.notify;

import java.util.List;

import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.MessageClientNotify;

public class MessageNotify implements Notify{

	private String error;
	private List<Giocatore> giocatori;
	/**
	 * @param error
	 * @param giocatori
	 */
	public MessageNotify(String error, List<Giocatore> giocatori) {
		this.error = error;
		this.giocatori = giocatori;
	}
	@Override
	public boolean daInviare(Giocatore giocatore) {
		if (giocatore == null)
			throw new NullPointerException("Il giocatore non può essere null");
		else
			return giocatori.contains(giocatore);
	}
	
	@Override
	public ClientNotify notifyToClientNotify() {
		return new MessageClientNotify(error);
	}
	




}
