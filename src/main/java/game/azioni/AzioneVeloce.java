package game.azioni;

import game.ErrorNotify;
import game.GameState;

public abstract class AzioneVeloce implements Azione {

	protected final GameState gameState;

	/**
	 * @param gameState
	 */
	public AzioneVeloce(GameState gameState) {
		super();
		this.gameState = gameState;
	}
	
	public void setStatoTransizioneVeloce(){
		gameState.getStato().transizioneVeloce(gameState);
	}
	
	public void setStatoTransizioneSecondaPrincipale(){
		gameState.getStato().transizioneSecondaPrincipale(gameState);
	}
	
	public void sendErrorNotify(){
		this.gameState.notifyObserver(new ErrorNotify());
	}
	
}
