package azioni;

import game.Partita;

public abstract class AzioneVeloce implements Azione {

	protected final Partita partita;

	/**
	 * @param partita
	 */
	public AzioneVeloce(Partita partita) {
		super();
		this.partita = partita;
	}
	
}
