package azioni;

import java.util.ArrayList;

import game.Regione;
import game.CartaPolitica;
import game.Consigliere;
import game.Partita;
import game.TesseraPermesso;

public class AcquistoTesseraPermesso extends AzionePrincipale {

	private final ArrayList<CartaPolitica> carteGiocatore;
	private final Regione regione;
	private final int indiceTesseraScoperta;
	/**
	 * constructor
	 * @param partita
	 * @param carteGiocatore
	 * @param regione
	 */
	public AcquistoTesseraPermesso(Partita partita, ArrayList<CartaPolitica> carteGiocatore, Regione regione, int indiceTesseraScoperta) {
		super(partita);
		this.carteGiocatore=carteGiocatore;
		this.regione=regione;
		this.indiceTesseraScoperta = indiceTesseraScoperta;
		}
	/**
	 * check if the color of cards passed are the same of balcone, and check if the player has enough
	 * money to do the action, then subtract the money due from the player
	 */
	@Override
	public boolean eseguiAzione() {
		if(carteGiocatore.isEmpty())
			return false;
		if(!controllaColori())
			return false;
		if(!paga(calcolaMonete()))
			return false;
		    for(CartaPolitica c: carteGiocatore){
		      this.partita.getGiocatoreCorrente().getCartePolitica().remove(c);
		      this.partita.getTabellone().getMazzoCartePolitica().aggiungiCarta(carteGiocatore);
		    }
		    TesseraPermesso tesseraScelta = regione.getTesserePermessoScoperte().get(indiceTesseraScoperta);
		    partita.getGiocatoreCorrente().getTesserePermesso().add(tesseraScelta);
		return true;
	}
	/**
	 * 
	 * @param moneteDovute
	 * @return true if the player can pay the money, false otherwise
	 */
	private boolean paga(int moneteDovute) {
		if(!this.partita.getGiocatoreCorrente().diminuisciRicchezza(moneteDovute))
			return false;
		return true;
	}

	/**
	 * 
	 * @return how many money the player have to pay
	 */
	private int calcolaMonete() {
		
		int monete=0;
		int carte=carteGiocatore.size();
		for (CartaPolitica carta: carteGiocatore ){
			if (carta.getColore().getColore()=="multicolor"){
				monete++;
			}
		}
		
		switch (carte){
		case 1:
		 monete=monete+10;
		 break;
		
		case 2:
		 monete=monete+7;
		 break;
		
		case 3:
		 monete=monete+4;
		break;
		
		default: 
		break;
		}
		return monete;
		
		
	}

	/**
	 * 
	 * @return false if the cards of the player didn't match
	 */
	private boolean controllaColori() {
		ArrayList<Consigliere> copiaConsiglieri = new ArrayList<Consigliere>(regione.getBalcone().getConsigliere());
		for (CartaPolitica carta: carteGiocatore ){
			if (carta.getColore().getColore()=="multicolor"){
				continue;
			}
				
			for (Consigliere consigliere: copiaConsiglieri){
					if (consigliere.getColore().getColore()==carta.getColore().getColore()){
						regione.getBalcone().getConsigliere().remove(consigliere);
					}
					
					else
						return false; //se la carta non matcha
				}
			}
		return true;
		
	}

}
