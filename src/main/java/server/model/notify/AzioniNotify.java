package server.model.notify;

import java.util.ArrayList;
import java.util.List;

import common.azioniDTO.AzioneDTO;
import server.model.azioni.Azione;
import server.model.game.Giocatore;
import server.view.clientNotify.AzioniClientNotify;
import server.view.clientNotify.ClientNotify;

public class AzioniNotify implements Notify {

	private List<Azione> azioni;
	private List<Giocatore> giocatori;

	/**
	 * for notify avaiable action
	 * 
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

	/**
	 * notified players
	 */
	@Override
	public boolean daInviare(Giocatore giocatore) {
		if (giocatore == null)
			throw new NullPointerException("Il giocatore non può essere null");
		else
			return giocatori.contains(giocatore);
	}

	/**
	 * create client notify
	 */
	@Override
	public ClientNotify notifyToClientNotify() {
		List<AzioneDTO> azioniDTO = new ArrayList<>();
		for (Azione a : azioni) {
			azioniDTO.add(a.getAzioneDTO());
		}
		return new AzioniClientNotify(azioniDTO);

	}

}
