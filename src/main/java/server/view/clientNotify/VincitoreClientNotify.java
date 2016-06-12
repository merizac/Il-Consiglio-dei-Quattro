package server.view.clientNotify;

import client.Grafica;
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
	 * @param giocatore
	 */
	public VincitoreClientNotify(Giocatore giocatore) {
		if(giocatore==null)
			throw new NullPointerException("Il giocatore non può essere null!");
		this.giocatoreDTO=new GiocatoreDTO();
		this.giocatoreDTO.inizializza(giocatore);
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stamp(Grafica grafica) {
		String messaggio ="Complimenti "+giocatoreDTO.getNome().toUpperCase()
				+ ", hai vinto!!";
		messaggio= messaggio + "Hai totalizzato "+ giocatoreDTO.getPunteggioVittoria() + " punti!";
		grafica.mostraMessaggio(messaggio);
	}

}
