package game.notify;

import java.util.List;

import game.Giocatore;
import view.clientNotify.ClientNotify;
import view.clientNotify.ErrorClientNotify;

public class ErrorNotify implements Notify{

	private String error;
	private List<Giocatore> giocatori;
	/**
	 * @param error
	 * @param giocatori
	 */
	public ErrorNotify(String error, List<Giocatore> giocatori) {
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
		return new ErrorClientNotify(error);
	}
	




}
