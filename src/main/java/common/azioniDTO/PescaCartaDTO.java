package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.Azione;

public class PescaCartaDTO implements AzioneDTO {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 4395569912555731424L;

	/**
	 * visitor
	 */
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

	/*
	 * 
	 * to string
	 */
	@Override
	public String toString() {
		return "Pesca [Pesca]";
	}

}
