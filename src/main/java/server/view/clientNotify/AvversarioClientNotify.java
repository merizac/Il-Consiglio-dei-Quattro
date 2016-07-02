package server.view.clientNotify;

import java.io.IOException;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;

public class AvversarioClientNotify implements ClientNotify {

	private static final long serialVersionUID = -8201798736821729448L;
	private GiocatoreDTO avversario;

	/**
	 * notify players that are not the current player
	 * 
	 * @param avversario
	 */
	public AvversarioClientNotify(GiocatoreDTO avversario) {
		this.avversario = avversario;
	}

	/**
	 * show avversario
	 */
	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) throws IOException {
		gameStateDTO.getAvversari().add(avversario);
		grafica.mostraAvversario(avversario);
	}

}
