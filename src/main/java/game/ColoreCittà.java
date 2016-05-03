/**
 * 
 */
package game;

import java.util.ArrayList;
import bonus.BonusTesseraPermesso;

public class ColoreCittà extends Colore {
	
	private ArrayList<CittàBonus> città;
	private final BonusTesseraPermesso bonusColore;
	/**
	 * @param colore
	 */
	public ColoreCittà(String colore, BonusTesseraPermesso bonus) {
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
	public BonusTesseraPermesso getBonusColore() {
		return bonusColore;
	}
	
	

}
