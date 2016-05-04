/**
 * 
 */
package game;


public class Strada {
	
	private final Città cittaPartenza;
	private final Città cittàDestinazione;
	/**
	 * @param cittaPartenza
	 * @param cittàDestinazione
	 */
	public Strada(Città cittaPartenza, Città cittàDestinazione) {
		super();
		this.cittaPartenza = cittaPartenza;
		this.cittàDestinazione = cittàDestinazione;
	}
	/**
	 * @return the cittaPartenza
	 */
	public Città getCittaPartenza() {
		return cittaPartenza;
	}
	/**
	 * @return the cittàDestinazione
	 */
	public Città getCittàDestinazione() {
		return cittàDestinazione;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cittaPartenza == null) ? 0 : cittaPartenza.hashCode());
		result = prime * result + ((cittàDestinazione == null) ? 0 : cittàDestinazione.hashCode());
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
		Strada other = (Strada) obj;
		if (cittaPartenza == null) {
			if (other.cittaPartenza != null)
				return false;
		} else if (!cittaPartenza.equals(other.cittaPartenza))
			return false;
		if (cittàDestinazione == null) {
			if (other.cittàDestinazione != null)
				return false;
		} else if (!cittàDestinazione.equals(other.cittàDestinazione))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Strada [cittaPartenza=" + cittaPartenza + ", cittàDestinazione=" + cittàDestinazione + "]";
	}

	

}
