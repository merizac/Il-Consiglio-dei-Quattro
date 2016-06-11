package server.model.game;

public class Consigliere  {

	private final Colore colore;
	
	public Consigliere(Colore colore){
		this.colore=colore;
	}
	/**
	 * return the value ofcColore
	 * @return colore
	 */
	public Colore getColore() {
		return this.colore;
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
		Consigliere other = (Consigliere) obj;
		if (colore == null) {
			if (other.colore != null)
				return false;
		} else if (!colore.equals(other.colore))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return colore.toString();
	}
	
	

}
	
	
