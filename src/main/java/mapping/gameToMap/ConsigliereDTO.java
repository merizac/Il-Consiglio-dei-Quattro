package mapping.gameToMap;

import java.io.Serializable;

public class ConsigliereDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7072033616848334700L;
	private String coloreConsigliere;
	/**
	 * @param coloreConsigliere
	 */
	public ConsigliereDTO(String coloreConsigliere) {
		this.coloreConsigliere = coloreConsigliere;
	}
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
	
	

}
