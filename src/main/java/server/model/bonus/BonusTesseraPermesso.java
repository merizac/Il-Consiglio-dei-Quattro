package server.model.bonus;

import server.model.azioni.Azione;
import server.model.azioni.azioniBonus.BonusTesseraPermessoN;
import server.model.game.GameState;

public class BonusTesseraPermesso extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8227026796102581003L;

	public BonusTesseraPermesso() {
		super();
	}
	
	/**
	 * @return the corresponding action
	 */
	public Azione getAzioneBonus(){
		return new BonusTesseraPermessoN();
		
	}

	/**
	 * add the corresponding action to the list of bonus in current player
	 */
	@Override
	public void usaBonus(GameState gameState) {		
		gameState.getGiocatoreCorrente().getBonusNobiltà().add(this.getAzioneBonus());
	}
	
	
}
