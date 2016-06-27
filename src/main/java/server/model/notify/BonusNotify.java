package server.model.notify;

import java.util.ArrayList;
import java.util.List;

import common.azioniDTO.AzioneDTO;
import server.model.azioni.Azione;
import server.model.game.Giocatore;
import server.view.clientNotify.BonusClientNotify;
import server.view.clientNotify.ClientNotify;

public class BonusNotify implements Notify {


	private Azione bonus;
	private List<Giocatore> giocatori;

	/**
	 * @param azioni
	 * @param giocatori
	 */
	public BonusNotify(Azione azione, List<Giocatore> giocatori) {
		if (azione == null)
			throw new NullPointerException("La lista di bonus non può essere null");
		if (giocatori == null)
			throw new NullPointerException("La lista di giocatori non può essere null");

		this.bonus = azione;
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
		return new BonusClientNotify(bonus.getAzioneDTO());
	}

}
