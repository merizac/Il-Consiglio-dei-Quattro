package game.azioni;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bonus.Bonus;
import game.Regione;
import game.CartaPolitica;
import game.Colore;
import game.Consigliere;
import game.GameState;
import game.TesseraPermesso;
import game.notify.MessageNotify;
import gameDTO.azioniDTO.AcquistoTesseraPermessoDTO;
import gameDTO.azioniDTO.AzioneDTO;
import utility.exception.AzioneNonEseguibile;

public class AcquistoTesseraPermesso extends AzionePrincipale implements Bonusable {

	private final int ID=2;
	private List<CartaPolitica> carteGiocatore;
	private Regione regione;
	private TesseraPermesso tesseraScoperta;

	
	/**
	 * check if the color of cards passed are the same of balcone, and check if the player has enough
	 * money to do the action, then subtract the money due from the player
	 * @throws AzioneNonEseguibile 
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		
		if(carteGiocatore.isEmpty()){
			gameState.notifyObserver(new MessageNotify("Errore: non sono presenti carte", Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}
			
		if(!controllaColori()){
			gameState.notifyObserver(new MessageNotify("Errore: i colori delle carte scelte non corrispondono con quelle del balcone!", Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}
			
		if(!paga(calcolaMonete(), gameState)){
			gameState.notifyObserver(new MessageNotify("Errore: i soldi non sono sufficienti!", Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}
			
		   
		for(CartaPolitica c: carteGiocatore){
		    
		      gameState.getGiocatoreCorrente().getCartePolitica().remove(c);
		      gameState.getMazzoCartePolitica().aggiungiCarte(carteGiocatore);
		    }
		
		regione.getTesserePermessoScoperte().remove(tesseraScoperta);
	    gameState.getGiocatoreCorrente().getTesserePermesso().add(tesseraScoperta);
	    
		regione.getTesserePermessoScoperte().add(regione.getMazzoTesserePermesso().pescaCarte());
		
		for (Bonus b:tesseraScoperta.getBonus()){
			System.out.println(b);
			b.usaBonus(gameState);
		}
		ArrayList<Bonus> bonusCasella = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();
		
		if(!bonusCasella.isEmpty()){	
				if(controlloBonus(gameState))	
					setStatoTransizionePrincipale(gameState); 
				else {
					try {
						gameState.getStato().transizioneBonus(gameState);
					} catch (AzioneNonEseguibile e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
			}
	
	/**
	 * 
	 * @param moneteDovute
	 * @return true if the player can pay the money, false otherwise
	 */
	private boolean paga(int moneteDovute, GameState gameState) {
		if(!gameState.getGiocatoreCorrente().diminuisciRicchezza(moneteDovute))
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
			if (carta.equals(new CartaPolitica(new Colore("Multicolore")))){
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
		List<Consigliere> copiaConsiglieri = new ArrayList<>(regione.getBalcone().getConsigliere());
		for (CartaPolitica carta: carteGiocatore ){
			boolean ok=false;
			if (carta.equals(new CartaPolitica(new Colore("Multicolore")))){
				continue;
			}
				
			for (Consigliere consigliere: copiaConsiglieri){
					if (!consigliere.getColore().equals(carta.getColore())){
						ok=false;
						continue;
					}
					
					else{
						ok=true;
						copiaConsiglieri.remove(consigliere);
						break;}
				}
			if(!ok) 
				return false; //se la carta non matcha
			}
		return true;
		
	}

	/**
	 * @return the carteGiocatore
	 */
	public List<CartaPolitica> getCarteGiocatore() {
		return carteGiocatore;
	}

	/**
	 * @param carteGiocatore the carteGiocatore to set
	 */
	public void setCarteGiocatore(List<CartaPolitica> carteGiocatore) {
		this.carteGiocatore = carteGiocatore;
	}

	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}

	/**
	 * @return the indiceTesseraScoperta
	 */
	public TesseraPermesso getTesseraScoperta() {
		return tesseraScoperta;
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new AcquistoTesseraPermessoDTO();
	}

	/**
	 * @param tesseraScoperta the tesseraScoperta to set
	 */
	public void setTesseraScoperta(TesseraPermesso tesseraScoperta) {
		if(tesseraScoperta==null)
			throw new IllegalArgumentException("La tessera permesso non può esserre null");
		this.tesseraScoperta = tesseraScoperta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcquistoTesseraPermesso other = (AcquistoTesseraPermesso) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	

}
