package game.azioni;

import game.ErrorNotify;
import game.GameState;

public abstract class AzionePrincipale implements Azione {
	
	protected final GameState gameState;

	/**
	 * @param partita
	 */
	public AzionePrincipale(GameState gameState) {
		super();
		this.gameState = gameState;
	}
	
	
	public void setStatoTransizionePrincipale(){
		gameState.getStato().transizionePrincipale(gameState);
	}
	
	public void sendErrorNotify(){
		this.gameState.notifyObserver(new ErrorNotify());
	}
	
}
