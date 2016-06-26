package server.view.clientNotify;

import java.io.IOException;
import java.util.List;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;

public class MarketClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -681898332225123065L;
	private List<GiocatoreDTO> giocatori;
	private boolean fineMarket;

	public MarketClientNotify(boolean fineMarket, List<GiocatoreDTO> giocatori) {
		this.giocatori = giocatori;
		this.fineMarket = fineMarket;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
	}

	@Override
	public void stamp(Grafica grafica) throws IOException {
		if (!fineMarket) {
			grafica.startMarket();
		} else {
			grafica.fineMarket();
		}
	}

}
