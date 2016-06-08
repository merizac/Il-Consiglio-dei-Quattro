package bonus;

import game.GameState;
import game.azioni.Azione;
import game.azioni.BonusTesseraAcquistataN;

public class BonusTesseraAcquistata extends Bonus {

	private boolean usata;
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
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the usata
	 */
	public boolean isUsata() {
		return usata;
	}

	/**
	 * @param usata the usata to set
	 */
	public void setUsata(boolean usata) {
		this.usata = usata;
	}

	
}
