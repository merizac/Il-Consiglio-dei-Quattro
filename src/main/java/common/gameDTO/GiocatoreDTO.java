package common.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.azioniDTO.AzioneDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.CartaPolitica;
import server.model.game.Giocatore;
import server.model.game.TesseraPermesso;

public class GiocatoreDTO implements Serializable {

	private static final long serialVersionUID = -2107420956593617286L;

	private String nome; 
	private ColoreDTO coloreGiocatore;
	private List<CartaPoliticaDTO> cartePolitica;
	private List<TesseraPermessoDTO> tesserePermesso;
	private List<TesseraPermessoDTO> tesserePermessoUsate; 
	private int tessereBonus;
	private int empori;
	private int aiutanti;
	private int punteggioVittoria;
	private int punteggioRicchezza;
	private int punteggioNobiltà;
	private List<AzioneDTO> bonusNobiltà;
	
	
	public GiocatoreDTO(){
		this.cartePolitica=new ArrayList<>();
		this.tesserePermesso= new ArrayList<>();
		this.tesserePermessoUsate =new ArrayList<>();
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
	public ColoreDTO getColoreGiocatore() {
		return coloreGiocatore;
	}
	/**
	 * @param coloreGiocatore the coloreGiocatore to set
	 */
	public void setColoreGiocatore(ColoreDTO coloreGiocatore) {
		this.coloreGiocatore = coloreGiocatore;
	}
	/**
	 * @return the cartePolitica
	 */
	public List<CartaPoliticaDTO> getCartePolitica() {
		return cartePolitica;
	}
	/**
	 * @param cartePolitica the cartePolitica to set
	 */
	public void setCartePolitica(List<CartaPoliticaDTO> cartePolitica) {
		this.cartePolitica = cartePolitica;
	}
	/**
	 * @return the tesserePermesso
	 */
	public List<TesseraPermessoDTO> getTesserePermesso() {
		return tesserePermesso;
	}
	/**
	 * @param tesserePermesso the tesserePermesso to set
	 */
	public void setTesserePermesso(List<TesseraPermessoDTO> tesserePermesso) {
		this.tesserePermesso = tesserePermesso;
	}
	/**
	 * @return the tesserePermessoUsate
	 */
	public List<TesseraPermessoDTO> getTesserePermessoUsate() {
		return tesserePermessoUsate;
	}
	/**
	 * @param tesserePermessoUsate the tesserePermessoUsate to set
	 */
	public void setTesserePermessoUsate(List<TesseraPermessoDTO> tesserePermessoUsate) {
		this.tesserePermessoUsate = tesserePermessoUsate;
	}
	/**
	 * @return the tessereBonus
	 */
	public int getTessereBonus() {
		return tessereBonus;
	}
	/**
	 * @param tessereBonus the tessereBonus to set
	 */
	public void setTessereBonus(int tessereBonus) {
		this.tessereBonus = tessereBonus;
	}
	/**
	 * @return the empori
	 */
	public int getEmpori() {
		return empori;
	}
	/**
	 * @param empori the empori to set
	 */
	public void setEmpori(int empori) {
		this.empori = empori;
	}
	/**
	 * @return the aiutanti
	 */
	public int getAiutanti() {
		return aiutanti;
	}
	/**
	 * @param aiutanti the aiutanti to set
	 */
	public void setAiutanti(int aiutanti) {
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
	public int getPunteggioNobiltà() {
		return punteggioNobiltà;
	}
	/**
	 * @param punteggioNobiltà the punteggioNobiltà to set
	 */
	public void setPunteggioNobiltà(int punteggioNobiltà) {
		this.punteggioNobiltà = punteggioNobiltà;
	}
	
	public void inizializza(Giocatore giocatore){

		this.setNome(giocatore.getNome());
		this.setAiutanti(giocatore.getAiutanti().getAiutante());
		this.cartePolitica=new ArrayList<>();
		for(CartaPolitica c: giocatore.getCartePolitica()){
			CartaPoliticaDTO cartaPoliticaDTO=new CartaPoliticaDTO();
			cartaPoliticaDTO.inizializza(c);
			this.cartePolitica.add(cartaPoliticaDTO);
		}
		ColoreDTO coloreDTO = new ColoreDTO();
		coloreDTO.inizializza(giocatore.getColoreGiocatore());
		this.setColoreGiocatore(coloreDTO);
		this.setEmpori(giocatore.getEmpori().size());
		this.setPunteggioNobiltà(giocatore.getPunteggioNobiltà().getPuntiNobiltà());
		this.setPunteggioVittoria(giocatore.getPunteggioVittoria());
		this.setPunteggioRicchezza(giocatore.getPunteggioRicchezza());
		this.setTesserePermesso(new ArrayList<>());
		for ( TesseraPermesso t: giocatore.getTesserePermesso()){
			TesseraPermessoDTO tesseraPermessoDTO = new TesseraPermessoDTO();
			tesseraPermessoDTO.inizializza(t);
			this.tesserePermesso.add(tesseraPermessoDTO);
		}
		this.setTesserePermessoUsate(new ArrayList<>());
		for ( TesseraPermesso t: giocatore.getTesserePermessoUsate()){
			TesseraPermessoDTO tesseraPermessoUsataDTO = new TesseraPermessoDTO();
			tesseraPermessoUsataDTO.inizializza(t);
			this.tesserePermessoUsate.add(tesseraPermessoUsataDTO);
		}
		int n=0;
		for(Bonus b:giocatore.getTessereBonus()){
			n=((BonusPuntiVittoria) b).getPuntiVittoria();
		}
		this.setTessereBonus(n);	
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n" + nome + " [ " + coloreGiocatore + " ]\ncartePolitica = "
				+ cartePolitica + "\ntesserePermesso = " + tesserePermesso + "\ntesserePermessoUsate = "
				+ tesserePermessoUsate + "\ntessereBonus=" + tessereBonus + "\nempori = " + empori + "\naiutanti = "
				+ aiutanti + "\npunteggioVittoria = " + punteggioVittoria + "\npunteggioRicchezza = "  + punteggioRicchezza
				+ "\npunteggioNobiltà = " + punteggioNobiltà;
	}
	/**
	 * @return the bonusNobiltà
	 */
	public List<AzioneDTO> getBonusNobiltà() {
		return bonusNobiltà;
	}
	/**
	 * @param bonus the bonusNobiltà to set
	 */
	public void setBonusNobiltà(List<AzioneDTO> bonus) {
		this.bonusNobiltà = bonus;
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
		if (!(obj instanceof GiocatoreDTO))
			return false;
		GiocatoreDTO other = (GiocatoreDTO) obj;
		if (coloreGiocatore == null) {
			if (other.coloreGiocatore != null)
				return false;
		} else if (!coloreGiocatore.equals(other.coloreGiocatore))
			return false;
		return true;
	}
	
	
	
}
