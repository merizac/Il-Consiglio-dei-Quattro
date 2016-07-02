package server.view.clientNotify;

import java.io.IOException;
import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;

public class MarketClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -681898332225123065L;
	private boolean fineMarket;

	/**
	 * notify of market states
	 * 
	 * @param fineMarket
	 */
	public MarketClientNotify(boolean fineMarket) {

		this.fineMarket = fineMarket;
	}

	/**
	 * show market graphic
	 */
	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) throws IOException {
		if (!fineMarket) {
			grafica.startMarket();
		} else {
			grafica.fineMarket();
		}
	}

}
