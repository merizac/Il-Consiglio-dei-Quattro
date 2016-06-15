package server.model.azioni.azioniBonus;

import java.util.Arrays;
import java.util.List;
import server.model.bonus.Bonus;
import server.model.game.GameState;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;


public interface Bonusable {

	public default boolean controlloBonus(GameState gameState){
		List<Bonus> bonusCasella = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();
		
		
			for(Bonus bonus: bonusCasella){
				bonus.usaBonus(gameState);
			}
			
			gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
			gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(), Arrays.asList(gameState.getGiocatoreCorrente())));
			if (gameState.getGiocatoreCorrente().getBonusNobiltà().isEmpty()){
				return true;
			}
			else {
				return false;
			}
		}
}
