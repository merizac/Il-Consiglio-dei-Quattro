package game;

import java.io.Serializable;
import java.util.ArrayList;
import bonus.*;


public class Giocatore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5783348541714689560L;
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
		this.nome=nome;
		this.cartePolitica=new ArrayList<>();
		this.tesserePermesso = new ArrayList<TesseraPermesso>();
		this.tesserePermessoUsate = new ArrayList<TesseraPermesso>();
		this.tessereBonus = new ArrayList<Bonus>();
		this.empori=new ArrayList<>();
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}



	/**
	 * @param nome the nome to set
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
	 * @param coloreGiocatore the coloreGiocatore to set
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
	 * @param aiutanti the aiutanti to set
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
	 * @param punteggioVittoria the punteggioVittoria to set
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
	 * @param punteggioRicchezza the punteggioRicchezza to set
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
	 * @param punteggioNobiltà the punteggioNobiltà to set
	 */
	public void setPunteggioNobiltà(PunteggioNobiltà punteggioNobiltà) {
		this.punteggioNobiltà = punteggioNobiltà;
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
	 * @return the empori
	 */
	public ArrayList<Emporio> getEmpori() {
		return empori;
	}



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


	public void rimuoviCartaPolitica(CartaPolitica cartaPolitica) {
		this.cartePolitica.remove(cartaPolitica);
	}
	
	public void aggiungiTesseraPermesso(TesseraPermesso tesseraPermesso){
		this.tesserePermesso.add(tesseraPermesso);
}


	public void rimuoviTesseraPermesso(TesseraPermesso tesseraPermesso) {
		this.tesserePermesso.remove(tesseraPermesso);
	}

	public void aggiungiEmpori(ArrayList<Emporio> empori){
		this.empori.addAll(empori);
	}
	
	public void creaEmpori(Colore colore){
		for(int i=0; i<10; i++){
			Emporio emporio=new Emporio(colore);
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
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Giocatore other = (Giocatore) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Giocatore [nome=" + nome + ", coloreGiocatore=" + coloreGiocatore + ", cartePolitica=" + cartePolitica
				+ ", tesserePermesso=" + tesserePermesso + ", tesserePermessoUsate=" + tesserePermessoUsate
				+ ", tessereBonus=" + tessereBonus + ", empori=" + empori + ", aiutanti=" + aiutanti
				+ ", punteggioVittoria=" + punteggioVittoria + ", punteggioRicchezza=" + punteggioRicchezza
				+ ", punteggioNobiltà=" + punteggioNobiltà + "]";
	}


	
	
	
	

}
