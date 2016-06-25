package common.gameDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import server.model.game.Città;
import server.model.game.Emporio;


public class CittàDTO implements Serializable{

	private static final long serialVersionUID = -2915919940093407446L;
	private String nome;
	private ColoreDTO ColoreDTO;
	private Set<String> empori;
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
	public Set<String> getEmpori() {
		return empori;
	}
	/**
	 * @param empori the empori to set
	 */
	public void setEmpori(Set<String> empori) {
		this.empori = empori;
	}

	/**
	 * map a città into a cittàDTO
	 * @param città
	 */
	public void inizializza(Città città){
		this.setNome(città.getNome());
		ColoreDTO coloreDTO=new ColoreDTO();
		coloreDTO.inizializza(città.getColoreCittà());
		this.setColoreDTO(coloreDTO);
		this.setEmpori(new HashSet<>());
		for(Emporio e: città.getEmpori()){
			this.getEmpori().add(e.getColore().getColore());
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
		if (!(obj instanceof CittàDTO))
			return false;
		CittàDTO other = (CittàDTO) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return nome + " Colore:" + ColoreDTO
				+ " Empori:" + empori ;
	}
	
	
}
