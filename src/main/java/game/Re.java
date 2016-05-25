package game;

import java.io.Serializable;

public class Re implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3727511371560962754L;
	private Città città;

	
	/**
	 * @param città
	 */
	public Re(Città città) {
		this.città = città;
	}


	/**
	 * @return the città
	 */
	public Città getCittà() {
		return città;
	}


	/**
	 * @param città the città to set
	 */
	public void setCittà(Città città) {
		this.città = città;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Re [città=" + città + "]";
	}

	
}
