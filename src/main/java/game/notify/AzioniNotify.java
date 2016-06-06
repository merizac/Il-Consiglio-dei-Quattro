package game.notify;

import java.util.List;

import game.Giocatore;
import game.azioni.Azione;
import view.clientNotify.AzioniClientNotify;
import view.clientNotify.ClientNotify;

public class AzioniNotify implements Notify{

	private List<Azione> azioni;
	private List<Giocatore> giocatori;

	/**
	 * @param azioni
	 * @param giocatori
	 */
	public AzioniNotify(List<Azione> azioni, List<Giocatore> giocatori) {
		if (azioni == null)
			throw new NullPointerException("La lista di azioni non può essere null");
		if (giocatori == null)
			throw new NullPointerException("La lista di giocatori non può essere null");

		this.azioni = azioni;
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
		return new AzioniClientNotify(azioni);

	}

}
