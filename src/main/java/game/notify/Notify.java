package game.notify;

import java.io.Serializable;

import game.GameState;

public interface Notify extends Serializable{
	
	public abstract void stamp(GameState gameState);
	
}
