package server.view.clientNotify;

import java.util.List;

import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;

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
	/*
	 * public void stamp(Grafica g){
	 * g.stampAzioni(azioni);
	 */

}
