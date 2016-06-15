package server.model.azioni.azioniVeloci;

import server.model.azioni.Azione;
import server.model.game.GameState;

public abstract class AzioneVeloce extends Azione {

	/**
	 * set quick transition in state pattern
	 * 
	 * @param gameState
	 */
	public void setStatoTransizioneVeloce(GameState gameState) {
		gameState.getStato().transizioneVeloce(gameState);
	}

	/**
	 * set second principal action in state pattern
	 * 
	 * @param gameState
	 */
	public void setStatoTransizioneSecondaPrincipale(GameState gameState) {
		gameState.getStato().transizioneSecondaPrincipale(gameState);
	}

}
