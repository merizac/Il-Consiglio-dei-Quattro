package game.notify;

import java.util.List;

import game.Giocatore;
import view.clientNotify.ClientNotify;
import view.clientNotify.MessageClientNotify;

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
