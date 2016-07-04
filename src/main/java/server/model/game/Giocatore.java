package server.model.game;

import java.util.ArrayList;
import java.util.List;

import server.model.azioni.Azione;
import server.model.bonus.*;

public class Giocatore {

	private String nome;
	private Colore coloreGiocatore;
	private final ArrayList<CartaPolitica> cartePolitica;
	private final ArrayList<TesseraPermesso> tesserePermesso;
	private final ArrayList<TesseraPermesso> tesserePermessoUsate;
	private final ArrayList<Bonus> tessereBonus;
	private final ArrayList<Emporio> empori;
	private Aiutante aiutanti;
	private int punteggioVittoria;
	private int punteggioRicchezza;
	private PunteggioNobiltà punteggioNobiltà;
	private List<Azione> bonusNobiltà;

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
	public Giocatore(String nome) {
		this.nome = nome;
		this.cartePolitica = new ArrayList<>();
		this.tesserePermesso = new ArrayList<>();
		this.tesserePermessoUsate = new ArrayList<>();
		this.tessereBonus = new ArrayList<>();
		this.empori = new ArrayList<>();
		this.bonusNobiltà = new ArrayList<>();
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the coloreGiocatore
	 */
	public Colore getColoreGiocatore() {
		return coloreGiocatore;
	}

	/**
	 * @param coloreGiocatore
	 *            the coloreGiocatore to set
	 */
	public void setColoreGiocatore(Colore coloreGiocatore) {
		this.coloreGiocatore = coloreGiocatore;
	}

	/**
	 * @return the aiutanti
	 */
	public Aiutante getAiutanti() {
		return aiutanti;
	}

	/**
	 * @param aiutanti
	 *            the aiutanti to set
	 */
	public void setAiutanti(Aiutante aiutanti) {
		this.aiutanti = aiutanti;
	}

	/**
	 * @return the punteggioVittoria
	 */
	public int getPunteggioVittoria() {
		return punteggioVittoria;
	}

	/**
	 * @param punteggioVittoria
	 *            the punteggioVittoria to set
	 */
	public void setPunteggioVittoria(int punteggioVittoria) {
		this.punteggioVittoria = punteggioVittoria;
	}

	/**
	 * @return the punteggioRicchezza
	 */
	public int getPunteggioRicchezza() {
		return punteggioRicchezza;
	}

	/**
	 * @param punteggioRicchezza
	 *            the punteggioRicchezza to set
	 */
	public void setPunteggioRicchezza(int punteggioRicchezza) {
		this.punteggioRicchezza = punteggioRicchezza;
	}

	/**
	 * @return the punteggioNobiltà
	 */
	public PunteggioNobiltà getPunteggioNobiltà() {
		return punteggioNobiltà;
	}

	/**
	 * @param punteggioNobiltà
	 *            the punteggioNobiltà to set
	 */
	public void setPunteggioNobiltà(PunteggioNobiltà punteggioNobiltà) {
		this.punteggioNobiltà = punteggioNobiltà;
	}

	/**
	 * @return the cartePolitica
	 */
	public List<CartaPolitica> getCartePolitica() {
		return cartePolitica;
	}

	/**
	 * @return the tesserePermesso
	 */
	public List<TesseraPermesso> getTesserePermesso() {
		return tesserePermesso;
	}

	/**
	 * @return the tesserePermessoUsate
	 */
	public List<TesseraPermesso> getTesserePermessoUsate() {
		return tesserePermessoUsate;
	}

	public int getNumeroTesserePermesso() {
		return this.getTesserePermesso().size() + this.getTesserePermessoUsate().size();
	}

	/**
	 * @return the tessereBonus
	 */
	public List<Bonus> getTessereBonus() {
		return tessereBonus;
	}

	/**
	 * @return the empori
	 */
	public List<Emporio> getEmpori() {
		return empori;
	}

	/**
	 * 
	 * @return bonus nobiltà
	 */
	public List<Azione> getBonusNobiltà() {
		return bonusNobiltà;
	}

	/**
	 * 
	 */
	public void aumentaRicchezza(int monete) {
		this.punteggioRicchezza = this.punteggioRicchezza + monete;
	}

	/**
	 * sub monete to variable punteggioRicchezza
	 * 
	 * @param monete
	 * @return true if it is possible to execute else false
	 */
	public boolean diminuisciRicchezza(int monete) {
		if (this.punteggioRicchezza >= monete) {
			this.punteggioRicchezza = this.punteggioRicchezza - monete;
			return true;
		} else
			return false;
	}

	/**
	 * add puntiVittoria to variable punteggioVittoria
	 * 
	 * @param puntiVittoria
	 */
	public void aumentaPuntiVittoria(int puntiVittoria) {
		this.punteggioVittoria = this.punteggioVittoria + puntiVittoria;
	}

	/**
	 * add politic card to the ArrayList CartePolitca of the player
	 * 
	 * @param carteDaAggiungere
	 */
	public void aggiungiCartaPolitica(CartaPolitica cartaDaAggiungere) {
		this.cartePolitica.add(cartaDaAggiungere);
	}

	/**
	 * remove politic card from player
	 * 
	 * @param cartaPolitica
	 */
	public void rimuoviCartaPolitica(CartaPolitica cartaPolitica) {
		this.cartePolitica.remove(cartaPolitica);
	}

	/**
	 * add tessera permesso to player
	 * 
	 * @param tesseraPermesso
	 */
	public void aggiungiTesseraPermesso(TesseraPermesso tesseraPermesso) {
		this.tesserePermesso.add(tesseraPermesso);
	}

	/**
	 * remove tessera permesso from player
	 * 
	 * @param tesseraPermesso
	 */
	public void rimuoviTesseraPermesso(TesseraPermesso tesseraPermesso) {
		this.tesserePermesso.remove(tesseraPermesso);
	}

	/**
	 * add emporiums
	 * 
	 * @param empori
	 */
	public void aggiungiEmpori(List<Emporio> empori) {
		this.empori.addAll(empori);
	}

	/**
	 * create emporium
	 * 
	 * @param colore
	 */
	public void creaEmpori(Colore colore) {
		for (int i = 0; i < 2; i++) {
			Emporio emporio = new Emporio(colore);
			empori.add(emporio);
		}
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coloreGiocatore == null) ? 0 : coloreGiocatore.hashCode());
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
		if (!(obj instanceof Giocatore))
			return false;
		Giocatore other = (Giocatore) obj;
		if (coloreGiocatore == null) {
			if (other.coloreGiocatore != null)
				return false;
		} else if (!coloreGiocatore.equals(other.coloreGiocatore))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Giocatore: " + nome + ", " + coloreGiocatore + "\ncartePolitica [" + cartePolitica + "]"
				+ "\n tesserePermesso [" + tesserePermesso + "]\ntesserePermessoUsate [" + tesserePermessoUsate
				+ "]\ntessereBonus [" + tessereBonus + "\nnumero empori = " + empori.size() + "\nnumero aiutanti = "
				+ aiutanti.getAiutante() + "\nPunteggio Vittoria = " + punteggioVittoria + "\nPunteggio Ricchezza = "
				+ punteggioRicchezza + "\nPunteggio Nobiltà = " + punteggioNobiltà.getPuntiNobiltà();
	}

}
