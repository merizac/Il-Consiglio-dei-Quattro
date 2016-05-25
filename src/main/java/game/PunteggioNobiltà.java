package game;

import java.io.Serializable;
import java.util.ArrayList;

import bonus.*;

public final class PunteggioNobiltà implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9088890013421921656L;
	private final int puntiNobiltà;
	private final ArrayList<Bonus> bonus;
	
	
	/**
	 * @param puntiNobiltà
	 * @param bonus
	 */
	public PunteggioNobiltà(int puntiNobiltà, ArrayList<Bonus> bonus) {
		this.puntiNobiltà = puntiNobiltà;
		this.bonus = bonus;
	}

	/**
	 * get bonus
	 * @return ArrayList<Bonus>
	 */
	public ArrayList<Bonus> getBonus() {
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
		return "PunteggioNobiltà [puntiNobiltà=" + puntiNobiltà + ", bonus=" + bonus + "]";
	}
	
	
	
	
	
}