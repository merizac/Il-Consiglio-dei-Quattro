package game.azioni;

import game.GameState;
import game.notify.ErrorNotify;
import utility.exception.AzioneNonEseguibile;

public abstract class AzioneVeloce extends Azione {

	/**
	 * @param gameState
	 */
	public AzioneVeloce(GameState gameState) {
		super(gameState);
	}
	
	public void setStatoTransizioneVeloce(){
		try {
			gameState.getStato().transizioneVeloce(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setStatoTransizioneSecondaPrincipale(){
		try {
			gameState.getStato().transizioneSecondaPrincipale(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendErrorNotify(){
		this.gameState.notifyObserver(new ErrorNotify());
	}
	
}
