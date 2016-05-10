package azioni;

import game.GameState;

public class IngaggioAiutante extends AzioneVeloce {

	public IngaggioAiutante(GameState gameState) {
		super(gameState);
		
	}
	/**
	 * @return true if the player has enough money, and add one aiutante
	 */
	@Override
	public boolean eseguiAzione() {
		if(gameState.getGiocatoreCorrente().diminuisciRicchezza(3))
		{
			gameState.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(1);
			return true;
		}
		return false;
	}
}
