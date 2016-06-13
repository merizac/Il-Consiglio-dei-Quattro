package server.view.clientNotify;

import client.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;

public class GiocatoreDisconnessoClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3480623132189967003L;
	private GiocatoreDTO giocatoreDisconnesso;

	/**
	 * @param giocatoreDisconnesso
	 */
	public GiocatoreDisconnessoClientNotify(GiocatoreDTO giocatoreDisconnesso) {
		this.giocatoreDisconnesso = giocatoreDisconnesso;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {

	}

	@Override
	public void stamp(Grafica grafica) {
		String messaggio = "Il giocatore "+giocatoreDisconnesso.getNome()+" ha abbandonato la partita\n";
		grafica.mostraMessaggio(messaggio);
	}

}
