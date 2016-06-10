package server.model.game;

import java.util.ArrayList;
import java.util.List;

import server.model.bonus.BonusPuntiVittoria;

public class ColoreCittà extends Colore  {

	private List<CittàBonus> città;
	private final BonusPuntiVittoria bonusColore;
	private boolean assegnatoBonus=false;
	/**
	 * @param colore
	 */
	public ColoreCittà(String colore, BonusPuntiVittoria bonus) {
		super(colore);
		this.bonusColore = bonus;
		this.città= new ArrayList<>();
	}
	/**
	 * @return the città
	 */
	public List<CittàBonus> getCittà() {
		return città;
	}
	/**
	 * @return the bonusColore
	 */
	public BonusPuntiVittoria getBonusColore() {
		return bonusColore;
	}
	/**
	 * @return the assegnatoBonus
	 */
	public boolean isAssegnatoBonus() {
		return assegnatoBonus;
	}
	/**
	 * @param assegnatoBonus the assegnatoBonus to set
	 */
	public void setAssegnatoBonus(boolean assegnatoBonus) {
		this.assegnatoBonus = assegnatoBonus;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bonusColore == null) ? 0 : bonusColore.hashCode());
		result = prime * result + ((città == null) ? 0 : città.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColoreCittà other = (ColoreCittà) obj;
		if (bonusColore == null) {
			if (other.bonusColore != null)
				return false;
		} else if (!bonusColore.equals(other.bonusColore))
			return false;
		if (città == null) {
			if (other.città != null)
				return false;
		} else if (!città.equals(other.città))
			return false;
		return true;
	}
	
	
	
	

}
