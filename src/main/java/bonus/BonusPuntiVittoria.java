package bonus;

import game.Partita;

public class BonusPuntiVittoria extends Bonus {

	private int puntiVittoria;

	/**
	 * @param partita
	 */
	public BonusPuntiVittoria(int puntiVittoria, Partita partita) {
		super(partita);
		this.puntiVittoria=puntiVittoria;	
	}
	/**
	 * add puntiVittoria to variable punteggioVittoria of Giocatore
	 */
	public void usaBonus() {
		this.getPartita().getGiocatoreCorrente().aumentaPuntiVittoria(puntiVittoria);
	}
}
