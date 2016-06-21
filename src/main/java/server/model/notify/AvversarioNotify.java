package server.model.notify;

import java.util.List;

import common.gameDTO.GiocatoreDTO;
import server.model.game.Giocatore;
import server.view.clientNotify.AvversarioClientNotify;

public class AvversarioNotify implements Notify {

	private Giocatore avversario;
	private List<Giocatore> giocatori;
	
	
	/**
	 * @param avversario
	 * @param giocatori
	 */
	public AvversarioNotify(Giocatore avversario, List<Giocatore> giocatori) {
		if(avversario==null)
			throw new IllegalArgumentException("L'avversario non può essere null");
		this.avversario = avversario;
		if(giocatori==null)
			throw new IllegalArgumentException("I giocatori non possono essere null");
		this.giocatori = giocatori;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	@Override
	public AvversarioClientNotify notifyToClientNotify() {
		GiocatoreDTO avversario= new GiocatoreDTO();
		avversario.inizializza(this.avversario);
		return new AvversarioClientNotify(avversario);
	}

}