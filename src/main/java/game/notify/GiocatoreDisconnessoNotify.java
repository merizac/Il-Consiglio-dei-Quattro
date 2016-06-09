package game.notify;

import java.util.List;

import game.Giocatore;
import gameDTO.gameDTO.GiocatoreDTO;
import view.clientNotify.ClientNotify;
import view.clientNotify.GiocatoreDisconnessoClientNotify;

public class GiocatoreDisconnessoNotify implements Notify {
	
	private Giocatore giocatoreDisconnesso;
	private List<Giocatore> giocatori;
	
	

	/**
	 * @param giocatoreDisconnesso
	 * @param giocatori
	 */
	public GiocatoreDisconnessoNotify(Giocatore giocatoreDisconnesso, List<Giocatore> giocatori) {
		this.giocatoreDisconnesso = giocatoreDisconnesso;
		this.giocatori = giocatori;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		return giocatori.contains(giocatore);
	}

	@Override
	public ClientNotify notifyToClientNotify() {
		GiocatoreDTO giocatoreDisconnesso= new GiocatoreDTO();
		giocatoreDisconnesso.inizializza(this.giocatoreDisconnesso);
		
		return new GiocatoreDisconnessoClientNotify(giocatoreDisconnesso);
	}

}
