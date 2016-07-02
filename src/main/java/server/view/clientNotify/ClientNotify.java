package server.view.clientNotify;

import java.io.IOException;
import java.io.Serializable;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;

public interface ClientNotify extends Serializable {

	/**
	 * show update in gameState or players
	 * 
	 * @param grafica
	 * @param gameStateDTO
	 * @throws IOException
	 */
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) throws IOException;
}
