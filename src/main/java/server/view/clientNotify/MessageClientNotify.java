package server.view.clientNotify;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;

public class MessageClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2793764378317071300L;
	private String error;
	/**
	 * @param error
	 */
	public MessageClientNotify(String error) {
		if(error==null)
			throw new NullPointerException("L'errore non può essere null");
		this.error = error;
	}


	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) {
		grafica.mostraMessaggio(error);
	}

}
