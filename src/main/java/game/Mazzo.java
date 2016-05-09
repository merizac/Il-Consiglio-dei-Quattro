package game;

import java.util.ArrayList;
import java.util.Collections;


public class Mazzo<T> {

	private ArrayList<T> carte;
	
	

	/**
	 * @param carte
	 */
	public Mazzo(ArrayList<T> carte) {
		this.carte = carte;
	}

	public Mazzo() {
		super();
	}

	/**
	 * @return the carte
	 */
	public ArrayList<T> getCarte() {
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
	 * @param carta
	 */
	public void aggiungiCarta(ArrayList<T> carta) {
		for(T t: carta){	
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
}
