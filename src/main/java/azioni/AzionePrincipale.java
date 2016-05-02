package azioni;
import game.Partita;

public abstract class AzionePrincipale implements Azione {
	
	protected final Partita partita;

	/**
	 * @param giocatoreCorrente
	 */
	public AzionePrincipale(Partita partita) {
		this.partita = partita;
	}
	
	
}
