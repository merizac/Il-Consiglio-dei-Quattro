package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.Azione;

public class SecondaAzionePrincipaleDTO implements AzioneDTO {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 5352888658813031850L;

	/**
	 * visitor pattern
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
		return "Seconda azione Principale  [V4]";
	}

}
