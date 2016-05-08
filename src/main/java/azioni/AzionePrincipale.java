package azioni;

import game.Partita;

public abstract class AzionePrincipale implements Azione {
	protected final Partita partita;

	/**
	 * @param partita
	 */
	public AzionePrincipale(Partita partita) {
		super();
		this.partita = partita;
	}
	
	
}
