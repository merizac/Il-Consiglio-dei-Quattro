package server.model.notify;

import java.util.List;

import common.gameDTO.GiocatoreDTO;
import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.GiocatoreMarketClientNotify;

public class GiocatoreMarketNotify implements Notify {
	
	private Giocatore giocatore;
	private List<Giocatore> giocatori;

	public GiocatoreMarketNotify(Giocatore giocatore, List<Giocatore> giocatori) {
		this.giocatore=giocatore;
		this.giocatori=giocatori;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	@Override
	public ClientNotify notifyToClientNotify() {
		GiocatoreDTO giocatoreDTO= new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
		
		return new GiocatoreMarketClientNotify(giocatoreDTO);
	}

}
