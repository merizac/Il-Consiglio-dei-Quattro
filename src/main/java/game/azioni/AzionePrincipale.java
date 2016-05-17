package game.azioni;

import game.GameState;
import game.notify.ErrorNotify;
import utility.exception.AzioneNonEseguibile;

public abstract class AzionePrincipale extends Azione {
	
	/**
	 * @param partita
	 */
	public AzionePrincipale(GameState gameState) {
		super(gameState);
	}
	
	
	public void setStatoTransizionePrincipale(){
		try {
			gameState.getStato().transizionePrincipale(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendErrorNotify(){
		this.gameState.notifyObserver(new ErrorNotify());
	}
	
}
