package common.gameDTO;

import java.io.Serializable;

import server.model.game.Consigliere;

public class ConsigliereDTO implements Serializable {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 7072033616848334700L;
	/**
	 * color of counselor
	 */
	private String coloreConsigliere;

	/**
	 */
	/**
	 * @return the coloreConsigliere
	 */
	public String getColoreConsigliere() {
		return coloreConsigliere;
	}

	/**
	 * @param coloreConsigliere
	 *            the coloreConsigliere to set
	 */
	public void setColoreConsigliere(String coloreConsigliere) {
		this.coloreConsigliere = coloreConsigliere;
	}

	/**
	 * initialization
	 * 
	 * @param consigliere
	 */
	public void inizializza(Consigliere consigliere) {
		this.coloreConsigliere = consigliere.getColore().getColore();
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return coloreConsigliere;
	}

}
