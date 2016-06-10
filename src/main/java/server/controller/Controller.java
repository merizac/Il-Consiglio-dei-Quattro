package server.controller;

import server.model.azioni.Azione;
import server.model.game.GameState;
import utility.Observer;

public class Controller implements Observer<Azione> {

	private GameState gameState;

	public Controller(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void update(Azione azione) {
			azione.eseguiAzione(gameState);
	}

	/**
	 * @return the gameState
	 */
	public GameState getGameState() {
		return gameState;
	}

}
