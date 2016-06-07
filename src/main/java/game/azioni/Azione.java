package game.azioni;

import game.GameState;
import game.Giocatore;
import gameDTO.azioniDTO.AzioneDTO;
import utility.exception.AzioneNonEseguibile;

public abstract class Azione {

	public abstract void eseguiAzione(GameState gameState) throws AzioneNonEseguibile;
	
	public boolean isTurno(Giocatore giocatore, GameState gameState){
		if(giocatore==null)
			throw new NullPointerException("Il giocatore non può essere null");
		if(gameState==null)
			throw new NullPointerException("Il gameState non può essere null");
		return(giocatore.equals(gameState.getGiocatoreCorrente()));
	}
	
	public abstract AzioneDTO getAzioneDTO();
}