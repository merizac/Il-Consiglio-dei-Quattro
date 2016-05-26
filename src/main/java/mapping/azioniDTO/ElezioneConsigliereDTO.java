package mapping.azioniDTO;

import java.io.Serializable;

import mapping.gameToMap.ConsigliereDTO;
import mapping.gameToMap.RegioneDTO;

public class ElezioneConsigliereDTO implements Serializable, AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4208578701329743704L;
	private ConsigliereDTO consigliereDTO;
	private RegioneDTO regioneDTO;
	/**
	 * @param consigliereDTO
	 * @param regioneDTO
	 */
	public ElezioneConsigliereDTO(ConsigliereDTO consigliereDTO, RegioneDTO regioneDTO) {
		this.consigliereDTO = consigliereDTO;
		this.regioneDTO = regioneDTO;
	}
	/**
	 * @return the consigliereDTO
	 */
	public ConsigliereDTO getConsigliereDTO() {
		return consigliereDTO;
	}
	/**
	 * @return the regioneDTO
	 */
	public RegioneDTO getRegioneDTO() {
		return regioneDTO;
	}
	
	

}
