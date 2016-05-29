package game.notify;

import gameDTO.gameDTO.GameStateDTO;

public abstract class NotifyGiocatoreCorrente implements Notify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4775959362356109483L;

	@Override
	public abstract void stamp(GameStateDTO gameState);

}
