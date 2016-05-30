package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;

public class SecondaAzionePrincipaleDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5352888658813031850L;

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

}
