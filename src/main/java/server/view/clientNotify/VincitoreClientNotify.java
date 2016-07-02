package server.view.clientNotify;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import server.model.game.Giocatore;

public class VincitoreClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -139988226344509096L;
	private GiocatoreDTO giocatoreDTO;

	/**
	 * notify winner player
	 * 
	 * @param giocatore
	 */
	public VincitoreClientNotify(Giocatore giocatore) {
		if (giocatore == null)
			throw new NullPointerException("Il giocatore non può essere null!");
		this.giocatoreDTO = new GiocatoreDTO();
		this.giocatoreDTO.inizializza(giocatore);
	}

	/**
	 * show messages that the player is the winner
	 */
	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) {
		String messaggio = "Complimenti " + giocatoreDTO.getNome().toUpperCase() + ", hai vinto!!";
		messaggio = messaggio + "Hai totalizzato " + giocatoreDTO.getPunteggioVittoria() + " punti!";
		grafica.mostraMessaggio(messaggio);
	}

}
