package server.view.clientNotify;

import java.io.Serializable;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;

public interface ClientNotify extends Serializable {
	public void update(GameStateDTO gameStateDTO);
	public void stamp(Grafica grafica);
}
