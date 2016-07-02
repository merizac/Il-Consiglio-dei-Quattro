package server.view.clientNotify;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;

public class MessageClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2793764378317071300L;
	private String messaggio;
	private boolean market;

	/**
	 * notify messages to player
	 * 
	 * @param messaggio
	 */
	public MessageClientNotify(String messaggio, boolean market) {
		if (messaggio == null)
			throw new NullPointerException("L'messaggioe non può essere null");
		this.messaggio = messaggio;
		this.market=market;
	}

	/**
	 * show messages
	 */
	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) {
		if(!market)
			grafica.mostraMessaggio(messaggio);
		else
			grafica.mostraMessaggioMarket(messaggio);
	}

}
