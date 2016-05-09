/**
 * 
 */
package game;

import java.util.ArrayList;
import bonus.BonusPuntiVittoria;

public class ColoreCittà extends Colore {
	
	private ArrayList<CittàBonus> città;
	private final BonusPuntiVittoria bonusColore;
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
