package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.gameDTO.GiocatoreDTO;
import server.model.azioni.Exit;
import utility.ParameterException;

public class ExitDTO implements AzioneDTO {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -3222018360249512953L;
	/**
	 * player that want to exit game
	 */
	private GiocatoreDTO giocatoreDTO;

	/**
	 * exit action
	 * 
	 * @param giocatoreDTO
	 */
	public ExitDTO(GiocatoreDTO giocatoreDTO) {
		this.giocatoreDTO = giocatoreDTO;
	}

	/**
	 * constructor
	 */
	public ExitDTO() {
		super();
	}

	/**
	 * visitor
	 */
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
