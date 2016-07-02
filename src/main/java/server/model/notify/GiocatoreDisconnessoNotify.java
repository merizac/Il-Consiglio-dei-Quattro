package server.model.notify;

import java.util.List;

import common.gameDTO.GiocatoreDTO;
import server.model.game.Giocatore;
import server.view.clientNotify.ClientNotify;
import server.view.clientNotify.GiocatoreDisconnessoClientNotify;

public class GiocatoreDisconnessoNotify implements Notify {

	private Giocatore giocatoreDisconnesso;
	private List<Giocatore> giocatori;

	/**
	 * notify if one player exit the game
	 * 
	 * @param giocatoreDisconnesso
	 * @param giocatori
	 */
	public GiocatoreDisconnessoNotify(Giocatore giocatoreDisconnesso, List<Giocatore> giocatori) {
		this.giocatoreDisconnesso = giocatoreDisconnesso;
		this.giocatori = giocatori;
	}

	/**
	 * notified players
	 */
	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	/**
	 * client notify
	 */
	@Override
	public ClientNotify notifyToClientNotify() {

		GiocatoreDTO giocatoreDisconnessoDTO= new GiocatoreDTO();
		giocatoreDisconnessoDTO.inizializza(this.giocatoreDisconnesso);
		
		return new GiocatoreDisconnessoClientNotify(giocatoreDisconnessoDTO);
	}

}
