package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.BalconeDTO;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.ConsigliereDTO;

public class ElezioneConsigliereDTO implements AzioneDTO {

	private static final long serialVersionUID = 4208578701329743704L;
	private ConsigliereDTO consigliereDTO;
	private BalconeDTO balconeDTO;
	
	/**
	 * @return the consigliereDTO
	 */
	public ConsigliereDTO getConsigliereDTO() {
		return consigliereDTO;
	}
	/**
	 * @return the regioneDTO
	 */
	public BalconeDTO getBalconeDTO() {
		return balconeDTO;
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
	public void setBalconeDTO(BalconeDTO balconeDTO) {
		this.balconeDTO = balconeDTO;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Eleggere un consigliere  [P1]";
	}
}
