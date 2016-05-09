package bonus;


import java.util.ArrayList;

import game.Partita;
import game.PunteggioNobiltà;

public class BonusPuntiNobiltà extends Bonus {

	private int puntiNobiltà;
	
	public BonusPuntiNobiltà(Partita partita, int puntiNobiltà) {
		super();
		this.puntiNobiltà=puntiNobiltà;
	}

	/**
	 * set new object PunteggioNobiltà at the player who won the bonus.
	 * if the new position contains bonus, then usaBonus()
	 */
	@Override
	public void usaBonus(Partita partita) {
		int puntiCorrenti = partita.getGiocatoreCorrente().getPunteggioNobiltà().getPuntiNobiltà();
		int puntiNuovi= puntiCorrenti+puntiNobiltà;
		PunteggioNobiltà nuovaPosizioneNobiltà = partita.getTabellone().getPlanciaRe().getPercorsoNobiltà().get(puntiNuovi);
		
		partita.getGiocatoreCorrente().setPunteggioNobiltà(nuovaPosizioneNobiltà);
		
		ArrayList<Bonus> bonusCasella = partita.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();
		
		if (!bonusCasella.isEmpty()) {
			for(Bonus bonus: bonusCasella){
				bonus.usaBonus(partita);
				}
			}
	}
}
