package game.notify;

import java.util.List;

import game.Giocatore;
import view.clientNotify.ClassificaClientNotify;
import view.clientNotify.ClientNotify;

public class ClassificaNotify implements Notify {
	
	private List<Giocatore> vincitori;
	private List<Giocatore> perdenti;
	private List<Giocatore> giocatori;
	
	

	/**
	 * @param vincitori
	 * @param perdenti
	 * @param giocatori
	 */
	public ClassificaNotify(List<Giocatore> vincitori, List<Giocatore> perdenti, List<Giocatore> giocatori) {
		if(vincitori==null)
			throw new NullPointerException("La lista di vincitori non può essere null");
		if(perdenti==null)
			throw new NullPointerException("La lista di perdenti non può essere null");
		if(giocatori==null)
			throw new NullPointerException("La lista di giocatori non può essere null");
		this.vincitori = vincitori;
		this.perdenti = perdenti;
		this.giocatori = giocatori;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	@Override
	public ClientNotify notifyToClientNotify() {
		return new ClassificaClientNotify(vincitori, perdenti);
	}

}
