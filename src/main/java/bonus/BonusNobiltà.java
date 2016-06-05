package bonus;

import game.GameState;
import utility.exception.AzioneNonEseguibile;

public abstract class BonusNobiltà extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 953130507727170967L;	
	
	@Override
	public void usaBonus(GameState gameState){
		gameState.getGiocatoreCorrente().getBonusNobiltà().add(this);
	}
	
	public void setStatoTransizioneBonus(GameState gameState){
		try {
			gameState.getStato().transizioneBonus(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
