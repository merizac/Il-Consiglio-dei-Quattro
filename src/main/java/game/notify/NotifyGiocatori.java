package game.notify;

import gameDTO.gameDTO.GameStateDTO;

public abstract class NotifyGiocatori implements Notify  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1543229339156004686L;

	public abstract void stamp(GameStateDTO gameState);

}
