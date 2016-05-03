package game;

public class Colore {
	private final String colore;
	/**
	 * the construct of the class Colore set the variable colore at the parameter passed
	 * @param colore
	 */
	public Colore(String colore){
	
		this.colore=colore;
	}
	/**
	 * return the value of the variable colore
	 * @return colore 
	 */
	public String getColore() {
		return colore;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colore == null) ? 0 : colore.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colore other = (Colore) obj;
		if (colore == null) {
			if (other.colore != null)
				return false;
		} else if (!colore.equals(other.colore))
			return false;
		return true;
	}
	
	

}
