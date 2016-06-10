package server.model.azioni;

import common.azioniDTO.AzioneDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;

public abstract class Azione {

	public abstract void eseguiAzione(GameState gameState);
	
	public boolean isTurno(Giocatore giocatore, GameState gameState){
		if(giocatore==null)
			throw new NullPointerException("Il giocatore non può essere null");
		if(gameState==null)
			throw new NullPointerException("Il gameState non può essere null");
		return(giocatore.equals(gameState.getGiocatoreCorrente()));
	}
	
	public abstract AzioneDTO getAzioneDTO();
}