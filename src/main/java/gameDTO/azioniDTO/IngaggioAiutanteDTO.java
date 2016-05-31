package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;

public class IngaggioAiutanteDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8961689927032439392L;

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

}
