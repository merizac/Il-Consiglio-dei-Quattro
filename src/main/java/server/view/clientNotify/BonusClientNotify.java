package server.view.clientNotify;

import java.util.ArrayList;
import java.util.List;

import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;

public class BonusClientNotify implements ClientNotify {

	private static final long serialVersionUID = -4132525333640241505L;
	private List<AzioneDTO> bonus;

	public BonusClientNotify(ArrayList<AzioneDTO> bonusDTO) {
		if(bonusDTO==null)
			throw new NullPointerException("I bonus non possono essere null");
		this.bonus=bonusDTO;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.getGiocatoreDTO().setBonusNobiltà(bonus);
	}

	@Override
	public void stamp() {
		System.out.println("Hai vinto un Bonus nel Percorso Nobiltà!\n");
		for(AzioneDTO b: bonus){
			System.out.println(b);
		}
	}
}
