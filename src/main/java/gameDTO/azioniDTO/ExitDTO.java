package gameDTO.azioniDTO;

import game.azioni.Exit;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.GiocatoreDTO;

public class ExitDTO implements AzioneDTO {

	private static final long serialVersionUID = -3222018360249512953L;
	private GiocatoreDTO giocatoreDTO;
	
	public ExitDTO(GiocatoreDTO giocatoreDTO){
		this.giocatoreDTO = giocatoreDTO;
	}

	@Override
	public Exit accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

	/**
	 * @return the giocatoreDTO
	 */
	public GiocatoreDTO getGiocatoreDTO() {
		return giocatoreDTO;
	}

}
