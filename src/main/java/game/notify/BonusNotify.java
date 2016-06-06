package game.notify;

import java.util.List;

import bonus.BonusNobiltà;
import game.Giocatore;
import view.clientNotify.BonusClientNotify;
import view.clientNotify.ClientNotify;

public class BonusNotify implements Notify {


	private List<BonusNobiltà> bonus;
	private List<Giocatore> giocatori;

	/**
	 * @param azioni
	 * @param giocatori
	 */
	public BonusNotify(List<BonusNobiltà> bonus, List<Giocatore> giocatori) {
		if (bonus == null)
			throw new NullPointerException("La lista di bonus non può essere null");
		if (giocatori == null)
			throw new NullPointerException("La lista di giocatori non può essere null");

		this.bonus = bonus;
		this.giocatori = giocatori;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		if (giocatore == null)
			throw new NullPointerException("Il giocatore non può essere null");
		else
			return giocatori.contains(giocatore);
	}
	@Override
	public ClientNotify notifyToClientNotify() {
		return new BonusClientNotify(bonus);

	}

}
