package server.view;

import server.model.azioni.Azione;
import server.model.game.GameState;
import server.model.notify.Notify;
import utility.Observable;
import utility.Observer;

public abstract class View extends Observable <Azione> implements Observer <Notify>{

	/**
	 * update a client
	 */
	@Override
	public void update(Notify notify) {
		
	}
	/**
	 * set the gameState
	 * @param gameState
	 */
	public abstract void setGameState(GameState gameState);
	/**
	 * disconnect the client
	 */
	public abstract void disconnetti();
}
