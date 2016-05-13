package game.azioni;

import java.util.ArrayList;
import java.util.HashSet;
import bonus.Bonus;
import game.Balcone;
import game.CartaPolitica;
import game.Città;
import game.CittàBonus;
import game.Colore;
import game.Consigliere;
import game.Emporio;
import game.GameState;
import game.Mappa;
import game.notify.ErrorParameterNotify;

public class CostruzioneAiutoRe extends AzionePrincipale {

	private final Città cittàCostruzione;
	private final Balcone balcone;
	private final ArrayList<CartaPolitica> carteGiocatore;
	
	public CostruzioneAiutoRe(GameState gameState, Città cittàCostruzione, ArrayList<CartaPolitica> carteGiocatore) {
		super(gameState);
		this.cittàCostruzione=cittàCostruzione;
		this.carteGiocatore=carteGiocatore;
		this.balcone=gameState.getPlanciaRe().getBalconeRe();
	}
	/**
	 * execute the action
	 */
	@Override
	public void eseguiAzione() {
		Mappa mappa= gameState.getMappa();
		/*PassaggioParametri passaggioParametri= new PassaggioParametri(gameState);
		carteGiocatore= new HashSet<CartaPolitica>(passaggioParametri.selezionaCarteGiocatore());
		cittàCostruzione=passaggioParametri.selezionaCittà();*/
		if(!controllaColori())
			gameState.notifyObserver(new ErrorParameterNotify("Errore: i colori delle carte scelte non corrispondon con quelli del balcone!"));
			
		
		int moneteDovute= calcolaMonete() + 
				mappa.minimaDistanza(gameState.getPedinaRe().getCittà(), cittàCostruzione);
		
		if(!paga(moneteDovute))
			gameState.notifyObserver(new ErrorParameterNotify("Errore: i soldi non sono sufficienti!"));
			
		if(!pagoAiutanti())
			gameState.notifyObserver(new ErrorParameterNotify("Errore: gli aiutanti non sono sufficienti!"));
			
		else{
			gameState.getPedinaRe().setCittà(cittàCostruzione);
			costruisci();
			prendiBonus();
		}
		setStatoTransizionePrincipale(); 
		
	}
	/**
	 * add an emporium to the city where the player wants to build
	 */
	private void costruisci(){
	    Emporio emporio = this.gameState.getGiocatoreCorrente().getEmpori().remove(0);
	    this.cittàCostruzione.aggiungiEmporio(emporio);
	  }
	  /**
	   * give to the player the bonus of the city connected to the city where he has built an
	   * emporium.
	   */
	  private void prendiBonus(){
	    Colore coloreEmporio = this.gameState.getGiocatoreCorrente().getColoreGiocatore();
	    HashSet<CittàBonus> cittàCollegate = this.gameState.getMappa().trovaCittà(cittàCostruzione, coloreEmporio);
	    for ( CittàBonus c: cittàCollegate){
	        for(Bonus b: c.getBonus()){
	          b.usaBonus(gameState);
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
		if(!this.gameState.getGiocatoreCorrente().diminuisciRicchezza(moneteDovute))
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
	      if(this.gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(numeroEmpori)){
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
