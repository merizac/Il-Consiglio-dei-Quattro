package game.azioni;

import game.GameState;
import game.notify.ErrorParameterNotify;

public class IngaggioAiutante extends AzioneVeloce {

	/**
	 * @return true if the player has enough money, and add one aiutante
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		if(!gameState.getGiocatoreCorrente().diminuisciRicchezza(3))
			gameState.notifyObserver(new ErrorParameterNotify("Errore: i soldi non sono sufficienti"));
		
		else {
			gameState.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(1);
			setStatoTransizioneVeloce(gameState); 
			}	
		}
}
