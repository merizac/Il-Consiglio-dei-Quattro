package view.clientNotify;

import java.util.List;

import game.azioni.Azione;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.gameDTO.GameStateDTO;

public class AzioniClientNotify implements ClientNotify {

	private static final long serialVersionUID = -4132525333640241505L;
	private List<AzioneDTO> azioni;

	public AzioniClientNotify(List<AzioneDTO> azioni) {
		if(azioni==null)
			throw new NullPointerException("Le azioni non possono essere null");
		this.azioni=azioni;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.setAzioni(azioni);	
	}

	@Override
	public void stamp() {
		for(AzioneDTO a: azioni){
			System.out.println(a);
		}
	}

}
