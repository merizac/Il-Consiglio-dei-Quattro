package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.Azione;
import utility.ParameterException;

public class AzioneMappaDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3768392220819817794L;
	private String mappa;

	/**
	 * @param mappa
	 */
	public AzioneMappaDTO(String mappa) {
		this.mappa = mappa;
	}
	
	public String getMappa(){
		return this.mappa;
	}

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return null;
	}

}
