package server.model.bonus;

import server.model.azioni.Azione;
import server.model.game.GameState;

public abstract class BonusNobiltà extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 953130507727170967L;	
	
	
	@Override
	public void usaBonus(GameState gameState){
		gameState.getGiocatoreCorrente().getBonusNobiltà().add(this.getAzioneBonus());
	}
	
	public abstract Azione getAzioneBonus();
	
	public void setStatoTransizioneBonus(GameState gameState){
			gameState.getStato().transizioneBonus(gameState);
	}

}
