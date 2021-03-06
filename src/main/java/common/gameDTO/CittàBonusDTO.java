package common.gameDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import server.model.bonus.Bonus;
import server.model.game.CittàBonus;
import server.model.game.Emporio;

public class CittàBonusDTO extends CittàDTO implements Serializable {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 6054052021956866160L;
	/**
	 * list of bonus in the city
	 */
	private List<Bonus> bonus;

	/**
	 * @return the bonus
	 */
	public List<Bonus> getBonus() {
		return bonus;
	}

	/**
	 * @param bonus
	 *            the bonus to set
	 */
	public void setBonus(List<Bonus> bonus) {
		this.bonus = bonus;
	}

	/**
	 * map a cittàBonus into a cittàBonusDTO
	 * 
	 * @param cittàBonus
	 */
	public void inizializza(CittàBonus cittàBonus) {
		this.setNome(cittàBonus.getNome());
		ColoreDTO coloreDTO = new ColoreDTO();
		coloreDTO.inizializza(cittàBonus.getColoreCittà());
		this.setColoreDTO(coloreDTO);
		this.setEmpori(new HashSet<>());
		for (Emporio e : cittàBonus.getEmpori()) {
			this.getEmpori().add(e.getColore().getColore());
		}

		this.setBonus(cittàBonus.getBonus());
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return this.getNome() + " Colore: " + this.getColoreDTO().getColore() + " Empori: " + this.getEmpori()
				+ " Bonus : " + bonus;
	}

	/*
	 * hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bonus == null) ? 0 : bonus.hashCode());
		return result;
	}

	/*
	 * equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof CittàBonusDTO))
			return false;
		CittàBonusDTO other = (CittàBonusDTO) obj;
		if (bonus == null) {
			if (other.bonus != null)
				return false;
		} else if (!bonus.equals(other.bonus))
			return false;
		return true;
	}

}
