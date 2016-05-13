package game;

import java.util.Queue;

import controller.StartEnd;
import controller.Stato;

public class Context {
	
	private Stato stato;
	private Giocatore giocatoreCorrente;
	private Queue<Giocatore> giocatori;
	/**
	 * @param stato
	 * @param giocatoreCorrente
	 * @param giocatori
	 */
	public Context(Giocatore giocatoreCorrente, Queue<Giocatore> giocatori) {
		this.stato = new StartEnd();
		this.giocatoreCorrente = giocatoreCorrente;
		this.giocatori = giocatori;
	}
	
	

}
