package azioni;

import java.util.HashSet;
import java.util.Set;

import bonus.Bonus;
import game.Balcone;
import game.CartaPolitica;
import game.Città;
import game.CittàBonus;
import game.Colore;
import game.Consigliere;
import game.Emporio;
import game.Mappa;
import game.Partita;

public class CostruzioneAiutoRe extends AzionePrincipale {

	private final Città cittàCostruzione;
	private final Balcone balcone;
	private final Set<CartaPolitica> carteGiocatore;
	
	public CostruzioneAiutoRe(Partita partita, Città cittàCostruzione, Balcone balcone, Set<CartaPolitica> carteGiocatore) {
		super(partita);
		this.cittàCostruzione=cittàCostruzione;
		this.balcone=balcone;
		this.carteGiocatore=carteGiocatore;
	}
	/**
	 * execute the action
	 */
	@Override
	public boolean eseguiAzione() {
		Mappa mappa= partita.getTabellone().getMappa();
		if(!controllaColori())
			return false;
		int moneteDovute= calcolaMonete() + 
				mappa.minimaDistanza(partita.getTabellone().getPedinaRe().getCittà(), cittàCostruzione);
		if(!paga(moneteDovute) && pagoAiutanti())
			return false;
		else{
			partita.getTabellone().getPedinaRe().setCittà(cittàCostruzione);
			costruisci();
			prendiBonus();
		}
		return false;
	}
	/**
	 * add an emporium to the city where the player wants to build
	 */
	private void costruisci(){
	    Emporio emporio = this.partita.getGiocatoreCorrente().getEmpori().remove(0);
	    this.cittàCostruzione.aggiungiEmporio(emporio);
	  }
	  /**
	   * give to the player the bonus of the city connected to the city where he has built an
	   * emporium.
	   */
	  private void prendiBonus(){
	    Colore coloreEmporio = this.partita.getGiocatoreCorrente().getColoreGiocatore();
	    HashSet<CittàBonus> cittàCollegate = this.partita.getTabellone().getMappa().trovaCittà(cittàCostruzione, coloreEmporio);
	    for ( CittàBonus c: cittàCollegate){
	        for(Bonus b: c.getBonus()){
	          b.usaBonus();
	    }
	    }
	  }
	    
	  /**
	   * check if the player has enough money to do the action, then it subtract the money due
	   * from the player 
	   * @param moneteDovute
	   * @return true if the player has enough money, false in the other case
	   */
	private boolean paga(int moneteDovute) {
		if(!this.partita.getGiocatoreCorrente().diminuisciRicchezza(moneteDovute))
			return false;
		return true;
	}
	/**
	 * check if the player has enough aiutanti to do the action, then it substract the aiutanti due
	 * from the player
	 * @return true if the player has enough aiutanti, false in the other case
	 */
	private boolean pagoAiutanti(){
	    int numeroEmpori = cittàCostruzione.getEmpori().size(); 
	  
	    if(!cittàCostruzione.getEmpori().isEmpty()) { 
	      if(this.partita.getGiocatoreCorrente().getAiutanti().togliAiutanti(numeroEmpori)){
	        return true;
	      }
	    }
	    return false;
	  }
	/**
	 * calculate the amount of money due by the player
	 * @return the value of money that the player has to pay
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
		case 1: if(carte==1){
		 monete=monete+10;
		}
		break;
		
		case 2: if(carte==2){
		 monete=monete+7;
		}
		break;
		case 3: if(carte==3){
		 monete=monete+4;
		}
		break;
		default: 
		break;
		}
		return monete;
		
		
	}
	/**
	 * check if the cards of the player match the counselors of the balcony
	 * @return true if the cards match, false in the other case
	 */
	private boolean controllaColori() {
		for (CartaPolitica carta: carteGiocatore ){
			if (carta.getColore().getColore()=="multicolor"){
				continue;
			}
				
			for (Consigliere consigliere: balcone.getConsigliere()){
					if (consigliere.getColore().getColore()==carta.getColore().getColore()){
						balcone.getConsigliere().remove(consigliere);
					}
					
					else
						return false; //se la carta non matcha
				}
			}
		return true;
		
	}
	
	
}
