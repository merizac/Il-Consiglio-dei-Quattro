package game.azioni;

import game.ErrorNotify;
import game.GameState;

public abstract class AzioneVeloce extends Azione {

	/**
	 * @param gameState
	 */
	public AzioneVeloce(GameState gameState) {
		super(gameState);
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
