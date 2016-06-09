package controller;

import utility.Observer;
import utility.exception.AzioneNonEseguibile;
import game.GameState;
import game.azioni.Azione;

public class Controller implements Observer<Azione> {

	private GameState gameState;

	public Controller(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void update(Azione azione) {
		try {
			azione.eseguiAzione(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return the gameState
	 */
	public GameState getGameState() {
		return gameState;
	}

}
