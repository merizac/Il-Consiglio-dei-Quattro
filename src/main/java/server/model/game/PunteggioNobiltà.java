package server.model.game;

import java.util.List;

import server.model.bonus.*;

public final class PunteggioNobiltà  {

	private final int puntiNobiltà;
	private final List<Bonus> bonus;
	
	
	/**
	 * @param puntiNobiltà
	 * @param bonus
	 */
	public PunteggioNobiltà(int puntiNobiltà, List<Bonus> bonus) {
		this.puntiNobiltà = puntiNobiltà;
		this.bonus = bonus;
	}

	/**
	 * get bonus
	 * @return ArrayList<Bonus>
	 */
	public List<Bonus> getBonus() {
		return this.bonus;
	}

	/**
	 * get puntiNobiltà
	 * @return puntiNobiltà
	 */
	public int getPuntiNobiltà() {
		return this.puntiNobiltà;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + puntiNobiltà;
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
		PunteggioNobiltà other = (PunteggioNobiltà) obj;
		if (puntiNobiltà != other.puntiNobiltà)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "puntiNobiltà = " + puntiNobiltà + ", bonus = [" + bonus + "]";
	}
	
	
	
	
	
}