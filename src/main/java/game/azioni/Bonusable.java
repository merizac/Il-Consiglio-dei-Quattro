package game.azioni;

import java.util.ArrayList;
import java.util.Arrays;

import bonus.Bonus;
import game.GameState;
import game.notify.GameStateNotify;
import game.notify.GiocatoreNotify;

public interface Bonusable {
	/*ArrayList<Bonus> bonusCasella = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();
	
	if (!bonusCasella.isEmpty()) {
		for(Bonus bonus: bonusCasella){
			bonus.usaBonus(gameState);
			}
		}*/
	
	public default boolean controlloBonus(GameState gameState){
		ArrayList<Bonus> bonusCasella = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();
		
		
			for(Bonus bonus: bonusCasella){
				bonus.usaBonus(gameState);
			}
			
			gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
			gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(), Arrays.asList(gameState.getGiocatoreCorrente())));
			if (gameState.getGiocatoreCorrente().getBonusNobiltà().isEmpty()){
				return true;
			}

			else return false;
		}
	
}
