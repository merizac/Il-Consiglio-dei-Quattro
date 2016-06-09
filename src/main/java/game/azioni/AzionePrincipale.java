package game.azioni;

import game.GameState;
import game.notify.MessageNotify;
import utility.exception.AzioneNonEseguibile;

public abstract class AzionePrincipale extends Azione {
	
	
	public void setStatoTransizionePrincipale(GameState gameState){
		try {
			gameState.getStato().transizionePrincipale(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
