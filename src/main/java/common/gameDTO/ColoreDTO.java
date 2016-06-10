package common.gameDTO;

import java.io.Serializable;

import server.model.game.Colore;

public class ColoreDTO implements Serializable{

	private static final long serialVersionUID = 97860141809311859L;
	private String colore;
	/**
	 * @return the colore
	 */
	public String getColore() {
		return colore;
	}
	/**
	 * @param colore the colore to set
	 */
	public void setColore(String colore) {
		this.colore = colore;
	}
	
	public void inizializza(Colore colore){
		this.setColore(colore.getColore());
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return colore ;
	}

}
