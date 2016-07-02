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

	/**
	 * notify the player in market states
	 * 
	 * @param giocatoreDTO
	 */
	public GiocatoreMarketClientNotify(GiocatoreDTO giocatoreDTO) {
		this.giocatoreDTO = giocatoreDTO;
	}

	/**
	 * show player in market
	 */
	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) throws IOException {

		gameStateDTO.setGiocatoreDTO(giocatoreDTO);

		grafica.mostraGiocatoreMarket(giocatoreDTO);
	}

}
