package server.model.notify;

import java.util.ArrayList;
import java.util.List;

import common.gameDTO.GiocatoreDTO;
import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.MarketClientNotify;

public class MarketNotify implements Notify {
	
	private List<Giocatore> giocatori;

	public MarketNotify(List<Giocatore> giocatori) {
		this.giocatori=giocatori;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	@Override
	public ClientNotify notifyToClientNotify() {
		List<GiocatoreDTO> giocatoriDTO=new ArrayList<>();
		for(Giocatore g: giocatori){
			GiocatoreDTO giocatoreDTO=new GiocatoreDTO();
			giocatoreDTO.inizializza(g);
			giocatoriDTO.add(giocatoreDTO);
		}
		
		return new MarketClientNotify(giocatoriDTO);
	}

}
