package common.gameDTO;

import java.io.Serializable;

import server.model.game.Re;

public class ReDTO implements Serializable {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -151592428460349836L;
	/**
	 * king's city
	 */
	private CittàDTO città;

	/**
	 * @return the città
	 */
	public CittàDTO getCittà() {
		return città;
	}

	/**
	 * @param città
	 *            the città to set
	 */
	public void setCittà(CittàDTO città) {
		this.città = città;
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "Il Re si trova nella città di " + città.getNome();
	}

	/**
	 * re initialization
	 * 
	 * @param re
	 */
	public void inizializza(Re re) {
		CittàDTO cittàDTO = new CittàDTO();
		cittàDTO.inizializza(re.getCittà());
		this.setCittà(cittàDTO);
	}

}
