package server.view.clientNotify;

import java.util.Arrays;
import java.util.List;

import client.grafica.Grafica;
import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;

public class BonusClientNotify implements ClientNotify {

	private static final long serialVersionUID = -4132525333640241505L;
	private AzioneDTO bonus;

	public BonusClientNotify(AzioneDTO azioneDTO) {
		if(azioneDTO==null)
			throw new NullPointerException("I bonus non possono essere null");
		this.bonus=azioneDTO;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.setAzioni(Arrays.asList(bonus));
	}

	@Override
	public void stamp(Grafica grafica) {
		grafica.mostraAzioni(Arrays.asList(bonus));
	}
}
