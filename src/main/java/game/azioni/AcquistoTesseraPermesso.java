package game.azioni;

import java.util.ArrayList;
import game.Regione;
import game.CartaPolitica;
import game.Consigliere;
import game.GameState;
import game.TesseraPermesso;

public class AcquistoTesseraPermesso extends AzionePrincipale {

	private ArrayList<CartaPolitica> carteGiocatore;
	private Regione regione;
	private int indiceTesseraScoperta;
	/**
	 * constructor
	 * @param partita
	 * @param carteGiocatore 
	 * @param carteGiocatore
	 * @param regione 
	 * @param regione
	 * @param indiceTesseraPermesso 
	 */
	public AcquistoTesseraPermesso(GameState gameState, ArrayList<CartaPolitica> carteGiocatore, Regione regione, int indiceTesseraPermesso) {
		super(gameState);
		this.carteGiocatore=carteGiocatore;
		this.regione=regione;
		this.indiceTesseraScoperta=indiceTesseraPermesso;
		}
	/**
	 * check if the color of cards passed are the same of balcone, and check if the player has enough
	 * money to do the action, then subtract the money due from the player
	 */
	@Override
	public boolean eseguiAzione() {
		/*PassaggioParametri passaggioParametri=new PassaggioParametri(partita);
		carteGiocatore=passaggioParametri.selezionaCarteGiocatore();
		regione=passaggioParametri.selezionaRegione();		
		indiceTesseraScoperta=passaggioParametri.selezionaTesseraScoperta(regione);*/
		
		if(carteGiocatore.isEmpty())
			return false;
		if(!controllaColori())
			return false;
		if(!paga(calcolaMonete()))
			return false;
		    for(CartaPolitica c: carteGiocatore){
		    
		    	
		      this.gameState.getGiocatoreCorrente().getCartePolitica().remove(c);
		      this.gameState.getMazzoCartePolitica().aggiungiCarta(carteGiocatore);
		    }
		    TesseraPermesso tesseraScelta = regione.getTesserePermessoScoperte().get(indiceTesseraScoperta);
		    gameState.getGiocatoreCorrente().getTesserePermesso().add(tesseraScelta);
		return true;
	}
	
	/*private int selezionaTesseraScoperta() {
		int indice=partita.getView().scegliTesseraScoperta(regione);
		
		return indice;
	}
	private Regione selezionaRegione() {
		String regione=partita.getView().scegliRegione();
		if(regione.equals("mare"))
			return partita.getTabellone().getRegioni().get(0);
		else if(regione.equals("montagna"))
			return partita.getTabellone().getRegioni().get(1);
		else
			return partita.getTabellone().getRegioni().get(2);
		
		}
	
	private ArrayList<CartaPolitica> selezionaCarteGiocatore () {

		ArrayList<String> carteView =partita.getView().scegliCarte();
		ArrayList<CartaPolitica> cartePolitica = new ArrayList<CartaPolitica>();
		for(String carta: carteView){
			int indice = Integer.parseInt(carta);
			while(indice<1 || indice>partita.getGiocatoreCorrente().getCartePolitica().size())
			{
				indice=partita.getView().erroreArrayList(carta);
			}
			cartePolitica.add(partita.getGiocatoreCorrente().getCartePolitica().get(indice-1));
		}
		
		return cartePolitica;
	}*/
	
	/**
	 * 
	 * @param moneteDovute
	 * @return true if the player can pay the money, false otherwise
	 */
	private boolean paga(int moneteDovute) {
		if(!this.gameState.getGiocatoreCorrente().diminuisciRicchezza(moneteDovute))
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
