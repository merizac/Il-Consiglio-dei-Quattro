package common.azioniDTO.azioneVisitor;

import server.model.azioni.Azione;
import utility.ParameterException;

public interface AzioneDaVisitare {
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException;
}
