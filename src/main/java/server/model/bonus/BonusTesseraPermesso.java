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
		// TODO Auto-generated constructor stub
	}
	
	public Azione getAzioneBonus(){
		return new BonusTesseraPermessoN();
	}

	@Override
	public void usaBonus(GameState gameState) {
		
	}

	
	
}
