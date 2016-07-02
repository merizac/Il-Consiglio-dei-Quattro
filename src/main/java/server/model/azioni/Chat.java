package server.model.azioni;

import common.azioniDTO.AzioneDTO;
import server.model.game.GameState;
import server.model.notify.MessageNotify;

public class Chat extends Azione{

	private String messaggio;
	
	@Override
	public void eseguiAzione(GameState gameState) {
		gameState.notifyObserver(new MessageNotify(messaggio , gameState.getGiocatori()));
		
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		// TODO Auto-generated method stub
		return null;
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
		if(messaggio==null)
			throw new NullPointerException("Non puoi spedire un messaggio vuoto");
		this.messaggio = messaggio;
	}

}
