package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.Azione;

public class PassaDTO implements AzioneDTO {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -4736599386993319723L;

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
		return "Passa  [Passa]";
	}

}
