package game.notify;

import java.util.List;

import game.Giocatore;
import view.clientNotify.ClientNotify;
import view.clientNotify.PerdenteClientNotify;
import view.clientNotify.VincitoreClientNotify;

public class PerdenteNotify implements Notify {

	private List<Giocatore> giocatori;
	private Giocatore giocatore;
	
	
	public PerdenteNotify(List<Giocatore> giocatori, Giocatore giocatore) {
		if(giocatori==null)
			throw new NullPointerException("La lista di giocatori non può essere null!");
		if(giocatore==null)
			throw new NullPointerException("Il giocatore non può essere null!");
		this.giocatori = giocatori;
		this.giocatore=giocatore;	
	}

	@Override
	public ClientNotify notifyToClientNotify() {
		return new PerdenteClientNotify(giocatore);
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

}
