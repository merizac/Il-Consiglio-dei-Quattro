package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.HashSet;
import game.Città;


public class CittàDTO implements Serializable{

	private static final long serialVersionUID = -2915919940093407446L;
	private String nome;
	private ColoreDTO ColoreDTO;
	private HashSet<String> empori;
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
	 * @return the ColoreDTO
	 */
	public ColoreDTO getColoreDTO() {
		return ColoreDTO;
	}
	/**
	 * @param ColoreDTO the ColoreDTO to set
	 */
	public void setColoreDTO(ColoreDTO ColoreDTO) {
		this.ColoreDTO = ColoreDTO;
	}
	/**
	 * @return the empori
	 */
	public HashSet<String> getEmpori() {
		return empori;
	}
	/**
	 * @param empori the empori to set
	 */
	public void setEmpori(HashSet<String> empori) {
		this.empori = empori;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public void inizializza(Città città){
		this.setNome(città.getNome());
		ColoreDTO coloreDTO=new ColoreDTO();
		coloreDTO.inizializza(città.getColoreCittà());
		this.setColoreDTO(coloreDTO);
		this.setEmpori(new HashSet<>());
	}
	
	@Override
	public String toString() {
		return "CITTA'" + nome + " Colore:" + ColoreDTO
				+ " empori:" + empori ;
	}
	
	
}
