package game.azioni;

import java.util.ArrayList;

import bonus.Bonus;
import game.GameState;

public interface Bonusable {
	/*ArrayList<Bonus> bonusCasella = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();
	
	if (!bonusCasella.isEmpty()) {
		for(Bonus bonus: bonusCasella){
			bonus.usaBonus(gameState);
			}
		}*/
	
	public default void controlloBonus(GameState gameState){
		ArrayList<Bonus> bonusCasella = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();
		
		if(!bonusCasella.isEmpty()){
			for(Bonus bonus: bonusCasella)
				bonus.usaBonus(gameState);
		}
		
	}
	
}
