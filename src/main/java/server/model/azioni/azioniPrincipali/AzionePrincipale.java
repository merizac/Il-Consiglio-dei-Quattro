package server.model.azioni.azioniPrincipali;

import server.model.azioni.Azione;
import server.model.game.GameState;

public abstract class AzionePrincipale extends Azione {
	
	/**
	 * do principal transition in state pattern
	 * @param gameState
	 */
	public void setStatoTransizionePrincipale(GameState gameState){
			gameState.getStato().transizionePrincipale(gameState);
	}
	
}
