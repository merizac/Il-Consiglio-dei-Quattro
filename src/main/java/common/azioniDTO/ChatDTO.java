package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.Azione;

public class ChatDTO implements AzioneDTO {

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
	 * get message
	 * 
	 * @return the messaggio
	 */
	public String getMessaggio() {
		return messaggio;
	}

	/**
	 * set message that player wrote
	 * 
	 * @param messaggio
	 *            the messagio to set
	 */
	public void setMessaggio(String messaggio) {
		if (messaggio == null)
			throw new NullPointerException("Inserisci il messaggio da inviare");
		this.messaggio = messaggio;
	}

}
