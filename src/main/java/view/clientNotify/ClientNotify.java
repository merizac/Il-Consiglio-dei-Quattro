package view.clientNotify;

import java.io.Serializable;
import gameDTO.gameDTO.GameStateDTO;

public interface ClientNotify extends Serializable {
	public void update(GameStateDTO gameStateDTO);
	public void stamp();
}
