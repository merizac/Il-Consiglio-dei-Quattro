package game.azioni;

import game.GameState;
import game.notify.ErrorNotify;
import utility.exception.AzioneNonEseguibile;

public abstract class AzioneVeloce extends Azione {


	public void setStatoTransizioneVeloce(GameState gameState){
		try {
			gameState.getStato().transizioneVeloce(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setStatoTransizioneSecondaPrincipale(GameState gameState){
		try {
			gameState.getStato().transizioneSecondaPrincipale(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendErrorNotify(GameState gameState){
		gameState.notifyObserver(new ErrorNotify());
	}
	
}
