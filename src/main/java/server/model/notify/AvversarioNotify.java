package server.model.notify;

import java.util.List;

import common.gameDTO.GiocatoreDTO;
import server.model.game.Giocatore;
import server.view.clientNotify.AvversarioClientNotify;

public class AvversarioNotify implements Notify {

	private Giocatore avversario;
	private List<Giocatore> giocatori;

	/**
	 * send to player that aren't the current player
	 * 
	 * @param avversario
	 * @param giocatori
	 */
	public AvversarioNotify(Giocatore avversario, List<Giocatore> giocatori) {
		if (avversario == null)
			throw new IllegalArgumentException("L'avversario non può essere null");
		this.avversario = avversario;
		if (giocatori == null)
			throw new IllegalArgumentException("I giocatori non possono essere null");
		this.giocatori = giocatori;
	}

	/**
	 * choose to who send the notification
	 */
	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	/**
	 * create the client notify
	 */
	@Override
	public AvversarioClientNotify notifyToClientNotify() {

		GiocatoreDTO avversarioDTO= new GiocatoreDTO();
		avversarioDTO.inizializza(this.avversario);
		return new AvversarioClientNotify(avversarioDTO);
	}

}
