package server.model.bonus;

import server.model.azioni.Azione;
import server.model.game.GameState;

public abstract class BonusNobiltà extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 953130507727170967L;	
	
	/**
	 * add the corresponding action to the list of bonus in current player
	 */
	@Override
	public void usaBonus(GameState gameState){
		gameState.getGiocatoreCorrente().getBonusNobiltà().add(this.getAzioneBonus());
	}
	
	/**
	 * 
	 * @return DTO action
	 */
	public abstract Azione getAzioneBonus();
	
	/**
	 * set transition in state pattern
	 * @param gameState
	 */
	public void setStatoTransizioneBonus(GameState gameState){
			gameState.getStato().transizioneBonus(gameState);
	}

}
