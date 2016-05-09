package bonus;

import game.Partita;

public class BonusMoneta extends Bonus {

	private int monete;
	/**
	 * constructor of BonusMoneta
	 * @param monete
	 * @param partita
	 */
	public BonusMoneta(int monete){
		super();
		this.monete=monete;
	}
	/**
	 * add monete to variable punteggioRicchezza of Giocatore
	 */
	public void usaBonus(Partita partita) {
		partita.getGiocatoreCorrente().aumentaRicchezza(monete);
	}
}
