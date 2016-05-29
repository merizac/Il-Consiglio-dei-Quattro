package game.notify;

import java.io.Serializable;

import game.GameState;
import gameDTO.gameDTO.GameStateDTO;

public interface Notify extends Serializable{
	
	public void stamp(GameStateDTO gameState);
	
	public void update(GameStateDTO gameStateDTO);
	
}
