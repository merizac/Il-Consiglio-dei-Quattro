package game;

public final class CartaPolitica {

	private final Colore colore;
	
	

	
	/**
	 * @param colore
	 */
	public CartaPolitica(Colore colore) {
		this.colore = colore;
	}


	/**
	 * @return the colore
	 */
	public Colore getColore() {
		return colore;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CartaPolitica [colore=" + colore + "]";
	}
	
	


}
