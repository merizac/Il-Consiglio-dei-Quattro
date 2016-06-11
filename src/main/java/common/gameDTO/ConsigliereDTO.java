package common.gameDTO;

import java.io.Serializable;

import server.model.game.Consigliere;

public class ConsigliereDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7072033616848334700L;
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
	 * @param coloreConsigliere the coloreConsigliere to set
	 */
	public void setColoreConsigliere(String coloreConsigliere) {
		this.coloreConsigliere = coloreConsigliere;
	}
	
	public void inizializza(Consigliere consigliere){
		this.coloreConsigliere=consigliere.getColore().getColore();
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return coloreConsigliere;
	}

}
