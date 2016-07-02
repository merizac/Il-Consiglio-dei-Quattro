package server.model.notify;

import java.util.ArrayList;
import java.util.List;

import common.gameDTO.OffertaDTO;
import server.model.game.Giocatore;
import server.model.market.Offerta;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.OffertaClientNotify;

public class OffertaNotify implements Notify {

	private List<Giocatore> giocatori;
	private List<Offerta> offerte;

	/**
	 * notify when one player do one offer
	 * 
	 * @param giocatori
	 * @param offerte
	 */
	public OffertaNotify(List<Offerta> offerte, List<Giocatore> giocatori) {
		this.giocatori = giocatori;
		this.offerte = offerte;
	}

	/**
	 * notified players
	 */
	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	/**
	 * create client notify
	 */
	@Override
	public ClientNotify notifyToClientNotify() {
		List<OffertaDTO> offerteDTO = new ArrayList<>();
		for (Offerta o : this.offerte) {
			OffertaDTO offertaDTO = new OffertaDTO();
			offertaDTO.inizializza(o);
			offerteDTO.add(offertaDTO);
		}
		return new OffertaClientNotify(offerteDTO);
	}

}
