package game.notify;

import game.GameState;

public abstract class NotifyGiocatori implements Notify  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1543229339156004686L;

	@Override
	public abstract void stamp(GameState gameState);

}
