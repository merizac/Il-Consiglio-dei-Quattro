package game.notify;

import game.GameState;

public abstract class NotifyGiocatoreCorrente implements Notify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4775959362356109483L;

	@Override
	public abstract void stamp(GameState gameState);

}
