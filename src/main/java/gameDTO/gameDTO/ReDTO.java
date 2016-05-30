package gameDTO.gameDTO;

import java.io.Serializable;

import game.Re;

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
		return "il Re si trova nella città di " + città.getNome();
	}
	
	public void inizializza(Re re){
		CittàDTO cittàDTO = new CittàDTO();
		cittàDTO.inizializza(re.getCittà());
		this.setCittà(cittàDTO);
	}

}
