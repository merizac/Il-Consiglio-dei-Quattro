package game.notify;

import java.util.List;

import game.GameState;
import game.azioni.Azione;
import gameDTO.gameDTO.GameStateDTO;

public class AzioniNotify extends NotifyGiocatoreCorrente {


	private static final long serialVersionUID = -7471237200273859021L;
	private List<String> azioni;

	public AzioniNotify(List<String> azioni) {
		this.azioni=azioni;
	}

	@Override
	public void stamp(GameStateDTO gameState) {
		for(String a: this.azioni)
		{
			System.out.println(a);
		}
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		// TODO Auto-generated method stub
		
	}

}
