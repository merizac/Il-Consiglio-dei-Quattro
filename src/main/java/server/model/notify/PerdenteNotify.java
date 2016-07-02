package server.model.notify;

import java.util.List;
import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.PerdenteClientNotify;

public class PerdenteNotify implements Notify {

	private List<Giocatore> giocatori;
	private Giocatore giocatore;

	/**
	 * notify players that are not the winner
	 * 
	 * @param giocatori
	 * @param giocatore
	 */
	public PerdenteNotify(List<Giocatore> giocatori, Giocatore giocatore) {
		if (giocatori == null)
			throw new NullPointerException("La lista di giocatori non può essere null!");
		if (giocatore == null)
			throw new NullPointerException("Il giocatore non può essere null!");
		this.giocatori = giocatori;
		this.giocatore = giocatore;
	}

	/**
	 * create client notify
	 */
	@Override
	public ClientNotify notifyToClientNotify() {
		return new PerdenteClientNotify(giocatore);
	}

	/**
	 * notified players
	 */
	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

}
