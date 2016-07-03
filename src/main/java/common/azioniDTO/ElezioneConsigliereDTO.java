package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.ElezioneConsigliereParametri;
import common.gameDTO.BalconeDTO;
import common.gameDTO.ConsigliereDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class ElezioneConsigliereDTO implements AzioneDTO, AzioneParametri {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 4208578701329743704L;
	/**
	 * Counselor from the stock
	 */
	private ConsigliereDTO consigliereDTO;
	/**
	 * balcony where set the counselor
	 */
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

	/**
	 * visitor
	 */
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);
	}

	/**
	 * @param consigliereDTO
	 *            the consigliereDTO to set
	 */
	public void setConsigliereDTO(ConsigliereDTO consigliereDTO) {
		this.consigliereDTO = consigliereDTO;
	}

	/**
	 * @param regioneDTO
	 *            the regioneDTO to set
	 */
	public void setBalconeDTO(BalconeDTO balconeDTO) {
		this.balconeDTO = balconeDTO;
	}

	/**
	 * set parameter
	 */
	@Override
	public ElezioneConsigliereParametri parametri() {
		return new ElezioneConsigliereParametri(this);
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "Eleggere un consigliere  [P1]";
	}
}
