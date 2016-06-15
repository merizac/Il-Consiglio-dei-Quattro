package server.view.clientNotify;

import java.util.List;

import client.grafica.Grafica;
import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;

public class BonusClientNotify implements ClientNotify {

	private static final long serialVersionUID = -4132525333640241505L;
	private List<AzioneDTO> bonus;

	public BonusClientNotify(List<AzioneDTO> bonusDTO) {
		if(bonusDTO==null)
			throw new NullPointerException("I bonus non possono essere null");
		this.bonus=bonusDTO;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.getGiocatoreDTO().setBonusNobiltà(bonus);
	}

	@Override
	public void stamp(Grafica grafica) {
		grafica.mostraAzioni(bonus);
	}
}
