package server.model.notify;

import java.util.List;

import common.gameDTO.GiocatoreDTO;
import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.GiocatoreClientNotify;

public class GiocatoreNotify implements Notify {

	private Giocatore giocatore;
	private List<Giocatore> giocatori;

	/**
	 * notify the change of the player (points, cards..)
	 * 
	 * @param giocatore
	 * @param giocatori
	 */
	public GiocatoreNotify(Giocatore giocatore, List<Giocatore> giocatori) {
		if (giocatore == null)
			throw new NullPointerException("Il giocatore non può essere null");
		if (giocatori == null)
			throw new NullPointerException("I giocatori non possono essere null");
		this.giocatore = giocatore;
		this.giocatori = giocatori;
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
		GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
		return new GiocatoreClientNotify(giocatoreDTO);
	}

}
