package server.view.clientNotify;

import java.io.IOException;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;

public class GiocatoreMarketClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8973783580062809721L;
	private GiocatoreDTO giocatoreDTO;

	public GiocatoreMarketClientNotify(GiocatoreDTO giocatoreDTO) {
		this.giocatoreDTO=giocatoreDTO;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.setGiocatoreDTO(giocatoreDTO);
	}

	@Override
	public void stamp(Grafica grafica) throws IOException {
		grafica.mostraGiocatoreMarket(giocatoreDTO);
	}

}
