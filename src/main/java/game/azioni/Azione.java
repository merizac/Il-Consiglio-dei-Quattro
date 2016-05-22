package game.azioni;

import game.GameState;
import utility.exception.AzioneNonEseguibile;

public abstract class Azione {
	
	public abstract void eseguiAzione(GameState gameState) throws AzioneNonEseguibile;
}