package game;

import java.util.ArrayList;

import bonus.*;

public class Giocatore {

	private final Colore coloreGiocatore;
	private final ArrayList<CartaPolitica> cartePolitica;
	private final ArrayList<TesseraPermesso> tesserePermesso;
	private final ArrayList<TesseraPermesso> tesserePermessoUsate;
	private final ArrayList<Bonus> tessereBonus;
	private final Aiutante aiutanti;
	private int punteggioVittoria;
	private int punteggioRicchezza;
	private PunteggioNobiltà punteggioNobiltà;

	

	/**
	 * @param coloreGiocatore
	 * @param cartePolitica
	 * @param tesserePermesso
	 * @param tesserePermessoUsate
	 * @param tessereBonus
	 * @param aiutanti
	 * @param punteggioVittoria
	 * @param punteggioRicchezza
	 * @param punteggioNobiltà
	 */
	public Giocatore(Colore coloreGiocatore, ArrayList<CartaPolitica> cartePolitica,
			Aiutante aiutanti, int punteggioVittoria, int punteggioRicchezza,
			PunteggioNobiltà punteggioNobiltà) {
		this.coloreGiocatore = coloreGiocatore;
		this.cartePolitica = cartePolitica;
		this.tesserePermesso = new ArrayList<TesseraPermesso>();
		this.tesserePermessoUsate = new ArrayList<TesseraPermesso>();
		this.tessereBonus = new ArrayList<Bonus>();
		this.aiutanti = aiutanti;
		this.punteggioVittoria = punteggioVittoria;
		this.punteggioRicchezza = punteggioRicchezza;
		this.punteggioNobiltà = punteggioNobiltà;
	}
	
	
	/**
	 * @return the punteggioNobiltà
	 */
	public PunteggioNobiltà getPunteggioNobiltà() {
		return punteggioNobiltà;
	}


	/**
	 * @return the coloreGiocatore
	 */
	public Colore getColoreGiocatore() {
		return coloreGiocatore;
	}


	/**
	 * @return the cartePolitica
	 */
	public ArrayList<CartaPolitica> getCartePolitica() {
		return cartePolitica;
	}


	/**
	 * @return the tesserePermesso
	 */
	public ArrayList<TesseraPermesso> getTesserePermesso() {
		return tesserePermesso;
	}


	/**
	 * @return the tesserePermessoUsate
	 */
	public ArrayList<TesseraPermesso> getTesserePermessoUsate() {
		return tesserePermessoUsate;
	}


	/**
	 * @return the tessereBonus
	 */
	public ArrayList<Bonus> getTessereBonus() {
		return tessereBonus;
	}


	/**
	 * @return the aiutanti
	 */
	public Aiutante getAiutanti() {
		return aiutanti;
	}
	
	/**
	 * @param punteggioNobiltà the punteggioNobiltà to set
	 */
	public void setPunteggioNobiltà(PunteggioNobiltà punteggioNobiltà) {
		this.punteggioNobiltà = punteggioNobiltà;
	}

	/**
	 * add monete to variable punteggioRicchezza
	 * @param monete
	 */
	public void aumentaRicchezza(int monete){
		this.punteggioRicchezza=this.punteggioRicchezza + monete;
	}
	/**
	 * sub monete to variable punteggioRicchezza
	 * @param monete
	 * @return true if it is possible to execute else false
	 */
	public boolean diminuisciRicchezza(int monete){
		if(this.punteggioRicchezza>=monete){
			this.punteggioRicchezza=this.punteggioRicchezza - monete;
			return true;
		}
		else return false;
	}
	/**
	 * add puntiVittoria to variable punteggioVittoria
	 * @param puntiVittoria
	 */
	public void aumentaPuntiVittoria(int puntiVittoria){
		this.punteggioVittoria=this.punteggioVittoria + puntiVittoria;
	}
	/**
	 * add politic card to the ArrayList CartePolitca of the player
	 * @param carteDaAggiungere
	 */
	public void aggiungiCartaPolitica(CartaPolitica cartaDaAggiungere){
			this.cartePolitica.add(cartaDaAggiungere);
	}
	
	

}
