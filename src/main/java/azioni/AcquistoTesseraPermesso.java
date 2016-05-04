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
		if (carteGiocatore.size()>regione.getBalcone().getConsigliere().size())
			return false;
		ArrayList<Consigliere> consiglieri=new ArrayList<Consigliere>(regione.getBalcone().getConsigliere());
		
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
		
	pagaCarte(monete, carteUguali);
		
		if(partita.getGiocatoreCorrente().diminuisciRicchezza(monete)){
			for(CartaPolitica c: carteGiocatore){
				this.partita.getGiocatoreCorrente().getCartePolitica().remove(c);
				this.partita.getTabellone().getMazzoCartePolitica().aggiungiCarta(carteGiocatore);
			}
			TesseraPermesso tesseraScelta = regione.getTesserePermessoScoperte().get(indiceTesseraScoperta);
			partita.getGiocatoreCorrente().getTesserePermesso().add(tesseraScelta);
			
		
			return true;
		}
			return false;	
	}
	
	private int pagaCarte (int monete,int carteUguali){
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
		default: 
		break;
		}
		return monete;
		
	}

}
