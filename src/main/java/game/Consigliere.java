package game;

public class Consigliere {

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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Consigliere [colore=" + colore + "]";
	}
	
	

}
	
	
