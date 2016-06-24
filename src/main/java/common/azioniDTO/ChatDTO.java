package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.Azione;

public class ChatDTO implements AzioneDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3055982333932543751L;
	private String messaggio;

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

	/**
	 * @return the messaggio
	 */
	public String getMessaggio() {
		return messaggio;
	}

	/**
	 * @param messaggio the messaggio to set
	 */
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

}
