package server.model.azioni.azioniBonus;

import java.util.Arrays;
import java.util.List;
import server.model.bonus.Bonus;
import server.model.game.GameState;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;


public interface Bonusable {
	
	/**
	 * AcquistoTesseraPermesso, CostruzioneAiutoRe and CostruzioneTesseraPermesso are Bonusable. 
	 * because are the actions in which the player can wins nobility interactive bonuses.
	 */
	
/**
 * for each bonus in the casella of nobility track, do 'usaBonus'.
 * notify gameState and info of current player
 * if the bonus is an interactive bonus, usaBonus add the corresponding action in array BonusNobiltà, otherwise use bonus
 * @param gameState
 * @return true if bonus is instance of of BonusNobiltà, false in other case
 */
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
