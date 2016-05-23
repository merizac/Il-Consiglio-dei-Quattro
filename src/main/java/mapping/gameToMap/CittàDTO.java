package mapping.gameToMap;

import java.io.Serializable;
import java.util.HashSet;

public class CittàDTO implements Serializable{

	private static final long serialVersionUID = -2915919940093407446L;
	private String nome;
	private RegioneDTO regioneDTO;
	private Colore coloreCittàDTO;
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
	 * @return the coloreCittàDTO
	 */
	public Colore getColoreCittàDTO() {
		return coloreCittàDTO;
	}
	/**
	 * @param coloreCittàDTO the coloreCittàDTO to set
	 */
	public void setColoreCittàDTO(Colore coloreCittàDTO) {
		this.coloreCittàDTO = coloreCittàDTO;
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
		return "CittàDTO [nome=" + nome + ", regioneDTO=" + regioneDTO + ", coloreCittàDTO=" + coloreCittàDTO
				+ ", empori=" + empori + "]";
	}
	
	
}
