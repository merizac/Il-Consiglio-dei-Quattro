package bonus;

import game.azioni.BonusTesseraAcquistataN;
import server.model.azioni.Azione;
import server.model.bonus.Bonus;
import server.model.game.GameState;

public class BonusTesseraAcquistata extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5973987175289643213L;

	public BonusTesseraAcquistata() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Azione getAzioneBonus(){
		return new BonusTesseraAcquistataN();
	}

	@Override
	public void usaBonus(GameState gameState) {		
		gameState.getGiocatoreCorrente().getBonusNobiltà().add(this.getAzioneBonus());
	}
	
}
