package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.gameDTO.GiocatoreDTO;
import server.model.azioni.Exit;
import utility.ParameterException;

public class ExitDTO implements AzioneDTO {

	private static final long serialVersionUID = -3222018360249512953L;
	private GiocatoreDTO giocatoreDTO;
	
	public ExitDTO(GiocatoreDTO giocatoreDTO){
		this.giocatoreDTO = giocatoreDTO;
	}

	@Override
	public Exit accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);
	}

	/**
	 * @return the giocatoreDTO
	 */
	public GiocatoreDTO getGiocatoreDTO() {
		return giocatoreDTO;
	}

}
