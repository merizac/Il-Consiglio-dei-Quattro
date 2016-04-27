package game;

public class Consigliere {

	private final Colore colore;
	private Boolean balcone;
	
	public Consigliere(String colore){
		this.colore=new Colore(colore);
		this.balcone=false;
	}
	/**
	 * return the value ofcColore
	 * @return colore
	 */
	public Colore getColore() {
		return this.colore;
	}
	/**
	 * return the value of balcone, true if Consigliere has a balcone and false in the other case
	 * @return balcone
	 */
	public Boolean getBalcone() {
		return this.balcone;
	}
	/**
	 * set balcone at the parameter passed 
	 * @param balcone
	 */
	public void setBalcone(Boolean balcone){
		this.balcone=balcone;
	}
}
	
	
