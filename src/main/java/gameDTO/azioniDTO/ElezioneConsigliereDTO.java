package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.RegioneDTO;

public class ElezioneConsigliereDTO implements AzioneDTO {

	private static final long serialVersionUID = 4208578701329743704L;
	private ConsigliereDTO consigliereDTO;
	private RegioneDTO regioneDTO;
	
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
	
	
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}
	/**
	 * @param consigliereDTO the consigliereDTO to set
	 */
	public void setConsigliereDTO(ConsigliereDTO consigliereDTO) {
		this.consigliereDTO = consigliereDTO;
	}
	/**
	 * @param regioneDTO the regioneDTO to set
	 */
	public void setRegioneDTO(RegioneDTO regioneDTO) {
		this.regioneDTO = regioneDTO;
	}
	
	

}
