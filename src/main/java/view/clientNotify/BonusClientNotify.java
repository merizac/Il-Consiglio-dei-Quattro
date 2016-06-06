package view.clientNotify;

import java.util.List;

import bonus.BonusNobiltà;
import gameDTO.gameDTO.GameStateDTO;

public class BonusClientNotify implements ClientNotify {

	private static final long serialVersionUID = -4132525333640241505L;
	private List<BonusNobiltà> bonus;

	public BonusClientNotify(List<BonusNobiltà> bonus) {
		if(bonus==null)
			throw new NullPointerException("I bonus non possono essere null");
		this.bonus=bonus;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.getGiocatoreDTO().setBonusNobiltà(bonus);
	}

	@Override
	public void stamp() {
		System.out.println("Hai vinto un Bonus nel Percorso Nobiltà!\n");
		for(BonusNobiltà b: bonus){
			System.out.println(b);
		}
	}
}
