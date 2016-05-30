package gameDTO.azioniDTO;

import java.io.Serializable;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;

public class PescaCartaDTO implements Serializable, AzioneDTO {

	private static final long serialVersionUID = 4395569912555731424L;

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

}
