package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.Azione;

public class IngaggioAiutanteDTO implements AzioneDTO {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 8961689927032439392L;

	/**
	 * visitor
	 */
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "IngaggioAiutante  [V1]";
	}

}
