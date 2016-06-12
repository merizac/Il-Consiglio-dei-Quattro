package server.view.clientNotify;

import java.util.List;

import client.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.OffertaDTO;

public class OffertaClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8490210977145419508L;
	private List<OffertaDTO> offerte;

	/**
	 * @param offerte
	 */
	public OffertaClientNotify(List<OffertaDTO> offerte) {
		this.offerte = offerte;
	}
	

	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.setOfferte(this.offerte);
	}

	@Override
	public void stamp(Grafica grafica) {
		grafica.mostraOfferte(offerte);
	}

}
