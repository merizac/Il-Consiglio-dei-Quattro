package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.ElezioneConsigliereParametri;
import common.gameDTO.BalconeDTO;
import common.gameDTO.ConsigliereDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class ElezioneConsigliereDTO implements AzioneDTO,AzioneParametri {

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
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
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
	
	@Override
	public ElezioneConsigliereParametri parametri(){
		return new ElezioneConsigliereParametri(this);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "P1";
	}
}
