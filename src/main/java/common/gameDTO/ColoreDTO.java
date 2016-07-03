package common.gameDTO;

import java.io.Serializable;

import server.model.game.Colore;

public class ColoreDTO implements Serializable {

	/*
	 * hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colore == null) ? 0 : colore.hashCode());
		return result;
	}

	/*
	 * equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ColoreDTO))
			return false;
		ColoreDTO other = (ColoreDTO) obj;
		if (colore == null) {
			if (other.colore != null)
				return false;
		} else if (!colore.equals(other.colore))
			return false;
		return true;
	}

	private static final long serialVersionUID = 97860141809311859L;
	private String colore;

	/**
	 * @return the colore
	 */
	public String getColore() {
		return colore;
	}

	/**
	 * @param colore
	 *            the colore to set
	 */
	public void setColore(String colore) {
		this.colore = colore;
	}

	/**
	 * map olore into coloreDTO
	 * 
	 * @param colore
	 */
	public void inizializza(Colore colore) {
		this.setColore(colore.getColore());
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return colore;
	}

}
