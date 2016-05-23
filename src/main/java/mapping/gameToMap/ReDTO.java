package mapping.gameToMap;

import java.io.Serializable;

public class ReDTO implements Serializable{
	
	private static final long serialVersionUID = -151592428460349836L;
	private CittàDTO città;
	/**
	 * @return the città
	 */
	public CittàDTO getCittà() {
		return città;
	}
	/**
	 * @param città the città to set
	 */
	public void setCittà(CittàDTO città) {
		this.città = città;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReDTO [città=" + città + "]";
	}
	
	

}
