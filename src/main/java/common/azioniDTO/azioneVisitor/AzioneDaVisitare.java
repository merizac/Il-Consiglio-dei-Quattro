package common.azioniDTO.azioneVisitor;

import server.model.azioni.Azione;

public interface AzioneDaVisitare {
	public Azione accept(AzioneVisitor azioneVisitor);
}
