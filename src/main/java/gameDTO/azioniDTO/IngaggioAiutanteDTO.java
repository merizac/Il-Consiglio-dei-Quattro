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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IngaggioAiutante  [V1]";
	}

}
