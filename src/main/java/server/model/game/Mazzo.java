package server.model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Mazzo<T> {

	private List<T> carte;
	
	/**
	 * @param carte
	 */
	public Mazzo(List<T> carte) {
		super();
		this.carte = carte;
	}

	public Mazzo() {
		carte=new ArrayList<>();
	}

	/**
	 * @return the carte
	 */
	public List<T> getCarte() {
		return carte;
	}

	/**
	 * 
	 * @return carta pescata
	 */
	public T pescaCarte() {
		return this.carte.remove(0);
	}

	/**
	 * shuffle the deck
	 */
	public void mescolaCarte() {
		Collections.shuffle(carte);
	}

	/**
	 * Add the list of cards at the end of the deck
	 * @param carteGiocatore
	 */
	public void aggiungiCarte(List<T> carteGiocatore) {
		for(T t: carteGiocatore){	
			carte.add(t);
		}
	}
	/**
	 * Add the card at the end of the deck
	 * @param carta
	 */
	public void aggiungiCarta(T carta) {
		carte.add(carta);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mazzo [ "+ carte + " ]";
	}
	
	
}
