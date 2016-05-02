package azioni;
import game.Partita;

public abstract class AzioneVeloce implements Azione {
	private final Partita partita;

	/**
	 * @param giocatoreCorrente
	 */
	public AzioneVeloce(Partita partita) {
		this.partita = partita;
	}
	
	
}
