/**
 * 
 */
package game;

import java.io.Serializable;
import java.util.ArrayList;
import bonus.BonusPuntiVittoria;

public class ColoreCittà extends Colore implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4867041496487133407L;
	private transient ArrayList<CittàBonus> città;
	private final transient BonusPuntiVittoria bonusColore;
	/**
	 * @param colore
	 */
	public ColoreCittà(String colore, BonusPuntiVittoria bonus) {
		super(colore);
		this.bonusColore = bonus;
		this.città= new ArrayList<CittàBonus>();
	}
	/**
	 * @return the città
	 */
	public ArrayList<CittàBonus> getCittà() {
		return città;
	}
	/**
	 * @return the bonusColore
	 */
	public BonusPuntiVittoria getBonusColore() {
		return bonusColore;
	}
	
	

}
