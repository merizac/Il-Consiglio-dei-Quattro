package game.azioni;

import game.GameState;

public class SecondaAzionePrincipale extends AzioneVeloce {


	/**
	 * if the player has already do the main action, fill the Array of Main Action 
	 * 			of the player for do another main action
	 * if the player is doing for the first time his action, and he choose first Speed Action, 
	 * 		at the end of the (second) main action, i have to refill the Array of Main Action
	 * @return false if it is not possible do the action for some reason
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		
		setStatoTransizioneSecondaPrincipale(gameState); 	
	}
}
