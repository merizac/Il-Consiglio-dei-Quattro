package server.model.azioni.azioniVeloci;

import server.model.azioni.Azione;
import server.model.game.GameState;

public abstract class AzioneVeloce extends Azione {


	public void setStatoTransizioneVeloce(GameState gameState){
			gameState.getStato().transizioneVeloce(gameState);
	}
	
	public void setStatoTransizioneSecondaPrincipale(GameState gameState){
			gameState.getStato().transizioneSecondaPrincipale(gameState);
	}
		
}
