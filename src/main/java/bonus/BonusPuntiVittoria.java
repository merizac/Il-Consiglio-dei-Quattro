package bonus;

import game.Partita;

public class BonusPuntiVittoria extends Bonus {

	private int puntiVittoria;

	/**
	 * @param partita
	 */
	public BonusPuntiVittoria(int puntiVittoria, Partita partita) {
		super();
		this.puntiVittoria=puntiVittoria;	
	}
	/**
	 * add puntiVittoria to variable punteggioVittoria of Giocatore
	 */
	public void usaBonus(Partita partita) {
		partita.getGiocatoreCorrente().aumentaPuntiVittoria(puntiVittoria);
	}
}
