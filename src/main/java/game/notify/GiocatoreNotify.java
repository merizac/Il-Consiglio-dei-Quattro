package game.notify;

import java.util.List;

import game.Giocatore;
import gameDTO.gameDTO.GiocatoreDTO;
import view.clientNotify.ClientNotify;
import view.clientNotify.GiocatoreClientNotify;

public class GiocatoreNotify implements Notify{

	private Giocatore giocatore;
	private List<Giocatore> giocatori;
	/**
	 * @param giocatore
	 * @param giocatori
	 */
	public GiocatoreNotify(Giocatore giocatore, List<Giocatore> giocatori) {
		if(giocatore==null)
			throw new NullPointerException("Il giocatore non può essere null");
		if(giocatori==null)
			throw new NullPointerException("I giocatori non possono essere null");
		this.giocatore = giocatore;
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
		GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
		
		return new GiocatoreClientNotify(giocatoreDTO);
	}
	
	

}
