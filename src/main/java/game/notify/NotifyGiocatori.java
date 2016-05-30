package game.notify;

import gameDTO.gameDTO.GameStateDTO;

public interface NotifyGiocatori extends Notify {
	public void update(GameStateDTO gameStateDTO);
}
