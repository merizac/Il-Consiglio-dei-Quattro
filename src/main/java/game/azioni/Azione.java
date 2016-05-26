package game.azioni;

import java.io.Serializable;

import game.GameState;
import utility.exception.AzioneNonEseguibile;

public abstract class Azione implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7438192254239489940L;

	public abstract void eseguiAzione(GameState gameState) throws AzioneNonEseguibile;
}