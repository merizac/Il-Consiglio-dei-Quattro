package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.Azione;
import utility.ParameterException;

public class AzioneMappaDTO implements AzioneDTO {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 3768392220819817794L;
	/**
	 * map that the first player had choose
	 */
	private String mappa;

	/**
	 * @param mappa
	 */
	public AzioneMappaDTO(String mappa) {
		this.mappa = mappa;
	}

	/**
	 * 
	 * @return mappa
	 */
	public String getMappa() {
		return this.mappa;
	}

	/**
	 * visitor pattern
	 */
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return null;
	}

}
