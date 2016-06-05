package game.azioni;

import java.util.ArrayList;

import bonus.Bonus;
import game.CittàBonus;
import game.GameState;

public class BonusGettoneN extends Azione {

	/*ottieni il bonus di un gettone ricompensa in cui hai un emporio.
	 * non puoi scegliere uno dei gettoni avanzamento ul percorso nobiltà
	 */
	
	private CittàBonus città;
	
	/**
	 * current player had to choose a city in which he has an emporio
	 * then the player took bonus that are on GettoneBonus in that city that he choose.
	 * 
	 * @param gameState
	 */
	@Override
	public void eseguiAzione(GameState gameState){
		ArrayList<Bonus> bonus= città.getBonus();
		for (Bonus b: bonus){
			b.usaBonus(gameState);
		}
	}

	/**
	 * @return the città
	 */
	public CittàBonus getCittà() {
		return città;
	}

	/**
	 * @param città the città to set
	 */
	public void setCittà(CittàBonus città) {
		this.città = città;
	}
	
}
