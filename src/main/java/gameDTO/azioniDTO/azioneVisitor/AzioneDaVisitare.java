package gameDTO.azioniDTO.azioneVisitor;

import game.azioni.Azione;

public interface AzioneDaVisitare {
	public Azione accept(AzioneVisitor azioneVisitor);
}
