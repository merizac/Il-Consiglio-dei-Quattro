package azioni;

import java.util.ArrayList;

import game.Balcone;
import game.CartaPolitica;
import game.Consigliere;
import game.Partita;

public class AcquistoTesseraPermesso extends AzionePrincipale {

	private final ArrayList<CartaPolitica> carteGiocatore;
	private final Balcone balcone;
	/**
	 * constructor
	 * @param partita
	 * @param carteGiocatore
	 * @param balcone
	 */
	public AcquistoTesseraPermesso(Partita partita, ArrayList<CartaPolitica> carteGiocatore, Balcone balcone) {
		super(partita);
		this.carteGiocatore=carteGiocatore;
		this.balcone=balcone;
		}
	/**
	 * check if the color of cards passed are the same of balcone, and check if the player has enough
	 * money to do the action, then subtract the money due from the player
	 */
	@Override
	public boolean eseguiAzione() {
		if (carteGiocatore.size()>balcone.getConsigliere().size())
			return false;
		ArrayList<Consigliere> consiglieri=new ArrayList<Consigliere>(balcone.getConsigliere());
		
		int carteUguali=0;
		int monete=0;
		
		for (CartaPolitica carta: carteGiocatore ){
			if (carta.getColore().getColore()=="multicolor"){
				carteUguali++;
				monete++;
				continue;
			}
				
			for (Consigliere consigliere: consiglieri){
					if (consigliere.getColore().getColore()==carta.getColore().getColore()){
						consiglieri.remove(consigliere);
						carteUguali++;
					}
					return false; //se la carta non matcha
				}
			}		
		
		switch (carteUguali){
		case 1: if(carteUguali==1){
		monete=monete+10;
		}
		break;
		
		case 2: if(carteUguali==2){
		monete=monete+7;
		}
		break;
		case 3: if(carteUguali==3){
		monete=monete+4;
		}
		break;
		default: break;
		}
		
		if(partita.getGiocatoreCorrente().diminuisciRicchezza(monete))
			return true;
		
		return false;	
	}
}
