package view.clientNotify;

import java.util.List;

import gameDTO.gameDTO.GameStateDTO;

public class AzioniClientNotify implements ClientNotify {

	private static final long serialVersionUID = -4132525333640241505L;
	private List<String> azioni;

	public AzioniClientNotify(List<String> azioni) {
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
		for(String a: azioni){
			System.out.println(a);
		}
	}

}
