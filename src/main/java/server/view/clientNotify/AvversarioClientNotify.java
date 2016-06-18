package server.view.clientNotify;

import java.io.IOException;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;

public class AvversarioClientNotify implements ClientNotify {

	private static final long serialVersionUID = -8201798736821729448L;
	private GiocatoreDTO avversario;
	
	/**
	 * @param avversario
	 */
	public AvversarioClientNotify(GiocatoreDTO avversario) {
		this.avversario = avversario;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.getAvversari().add(avversario);
	}

	@Override
	public void stamp(Grafica grafica) throws IOException {
		grafica.mostraAvversario(avversario);
	}

}
