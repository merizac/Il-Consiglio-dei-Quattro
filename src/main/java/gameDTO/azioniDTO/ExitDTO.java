package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.GiocatoreDTO;

public class ExitDTO implements AzioneDTO {

	private static final long serialVersionUID = -3222018360249512953L;
	private GiocatoreDTO giocatoreDTO;
	
	public ExitDTO(GiocatoreDTO giocatoreDTO){
		this.giocatoreDTO = giocatoreDTO;
	}

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
