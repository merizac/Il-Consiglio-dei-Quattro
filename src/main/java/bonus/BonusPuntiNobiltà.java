package bonus;


import java.util.ArrayList;
import game.GameState;
import game.PunteggioNobiltà;

public class BonusPuntiNobiltà extends Bonus {

	private int puntiNobiltà;
	
	public BonusPuntiNobiltà(int puntiNobiltà) {
		super();
		this.puntiNobiltà=puntiNobiltà;
	}

	/**
	 * set new object PunteggioNobiltà at the player who won the bonus.
	 * if the new position contains bonus, then usaBonus()
	 */
	@Override
	public void usaBonus(GameState gameState) {
		int puntiCorrenti = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getPuntiNobiltà();
		int puntiNuovi= puntiCorrenti+puntiNobiltà;
		PunteggioNobiltà nuovaPosizioneNobiltà = gameState.getPlanciaRe().getPercorsoNobiltà().get(puntiNuovi);
		
		gameState.getGiocatoreCorrente().setPunteggioNobiltà(nuovaPosizioneNobiltà);
		
		ArrayList<Bonus> bonusCasella = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();
		
		if (!bonusCasella.isEmpty()) {
			for(Bonus bonus: bonusCasella){
				bonus.usaBonus(gameState);
				}
			}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusPuntiNobiltà [puntiNobiltà=" + puntiNobiltà + "]";
	}
	
	
}
