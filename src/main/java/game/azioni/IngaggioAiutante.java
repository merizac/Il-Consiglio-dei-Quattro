package game.azioni;

import game.GameState;
import game.notify.ErrorNotify;
import game.notify.GiocatoreDTONotify;

public class IngaggioAiutante extends AzioneVeloce {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1641309249136321458L;

	/**
	 * @return true if the player has enough money, and add one aiutante
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		if(!gameState.getGiocatoreCorrente().diminuisciRicchezza(3)){
			gameState.notifyObserver(new ErrorNotify("Errore: i soldi non sono sufficienti"));
			return;
		}
		
		else {
			gameState.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(1);
			setStatoTransizioneVeloce(gameState); 
			gameState.notifyObserver(new GiocatoreDTONotify(gameState.getGiocatoreCorrente()));
			}	
		}
}
