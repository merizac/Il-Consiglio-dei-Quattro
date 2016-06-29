package server.view.clientNotify;

import java.io.IOException;
import java.io.Serializable;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;

public interface ClientNotify extends Serializable {
	
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) throws IOException;
}
