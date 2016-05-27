package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.HashSet;

public class CittàDTO implements Serializable{

	private static final long serialVersionUID = -2915919940093407446L;
	private String nome;
	private RegioneDTO regioneDTO;
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
	 * @return the regioneDTO
	 */
	public RegioneDTO getRegioneDTO() {
		return regioneDTO;
	}
	/**
	 * @param regioneDTO the regioneDTO to set
	 */
	public void setRegioneDTO(RegioneDTO regioneDTO) {
		this.regioneDTO = regioneDTO;
	}
	/**
	 * @return the ColoreDTODTOCittàDTO
	 */
	public ColoreDTO getColoreDTO() {
		return ColoreDTO;
	}
	/**
	 * @param ColoreDTODTOCittàDTO the ColoreDTODTOCittàDTO to set
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
	@Override
	public String toString() {
		return "CittàDTO [nome=" + nome + ", regioneDTO=" + regioneDTO + ", ColoreDTODTOCittàDTO=" + ColoreDTO
				+ ", empori=" + empori + "]";
	}
	
	
}
