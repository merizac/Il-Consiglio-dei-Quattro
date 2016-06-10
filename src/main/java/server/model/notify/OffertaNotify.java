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
	 * @param giocatori
	 * @param offerte
	 */
	public OffertaNotify(List<Offerta> offerte,List<Giocatore> giocatori) {
		this.giocatori = giocatori;
		this.offerte = offerte;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	@Override
	public ClientNotify notifyToClientNotify() {
		List<OffertaDTO> offerte=new ArrayList<>();
		for(Offerta o: this.offerte)
		{
			OffertaDTO offertaDTO=new OffertaDTO();
			offertaDTO.inizializza(o);
			offerte.add(offertaDTO);
		}
		return new OffertaClientNotify(offerte);
	}

}
