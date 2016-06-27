package server.model.notify;

import java.util.List;

import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.StartGiocatoreClientNotify;

public class StartGiocatoreNotify implements Notify {

	private List<Giocatore> giocatori;
	
	public StartGiocatoreNotify(List<Giocatore> giocatori) {
		this.giocatori=giocatori;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	@Override
	public ClientNotify notifyToClientNotify() {
		return new StartGiocatoreClientNotify();
	}

}
