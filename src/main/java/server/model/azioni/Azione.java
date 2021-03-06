package server.model.azioni;

import common.azioniDTO.AzioneDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;

public abstract class Azione {

	/**
	 * execute action
	 * @param gameState
	 */
	public abstract void eseguiAzione(GameState gameState);
	
	/**
	 * check if is the turn of the player
	 * @param giocatore
	 * @param gameState
	 * @return true if the player is the current player
	 */
	public boolean isTurno(Giocatore giocatore, GameState gameState){
		if(giocatore==null)
			throw new NullPointerException("Il giocatore non può essere null");
		if(gameState==null)
			throw new NullPointerException("Il gameState non può essere null");
		return giocatore.equals(gameState.getGiocatoreCorrente());
	}
	
	/**
	 * 
	 * @return DTO action
	 */
	public abstract AzioneDTO getAzioneDTO();
}