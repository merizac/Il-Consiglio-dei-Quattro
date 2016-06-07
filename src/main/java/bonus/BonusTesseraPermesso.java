package bonus;

import game.GameState;
import game.azioni.Azione;
import game.azioni.BonusTesseraPermessoN;

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
