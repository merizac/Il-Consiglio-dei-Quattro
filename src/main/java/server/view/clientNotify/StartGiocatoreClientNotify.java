package server.view.clientNotify;

import java.io.IOException;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;

public class StartGiocatoreClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3008913317361336935L;

	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) throws IOException {
			grafica.scegliMappa();
	}

}
