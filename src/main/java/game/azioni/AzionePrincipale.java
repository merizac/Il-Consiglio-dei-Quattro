package game.azioni;

import game.GameState;
import game.notify.ErrorNotify;

public abstract class AzionePrincipale extends Azione {
	
	/**
	 * @param partita
	 */
	public AzionePrincipale(GameState gameState) {
		super(gameState);
	}
	
	
	public void setStatoTransizionePrincipale(){
		gameState.getStato().transizionePrincipale(gameState);
	}
	
	public void sendErrorNotify(){
		this.gameState.notifyObserver(new ErrorNotify());
	}
	
}
