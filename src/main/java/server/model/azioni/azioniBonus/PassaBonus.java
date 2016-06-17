package server.model.azioni.azioniBonus;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.PassaBonusDTO;
import server.model.azioni.Azione;
import server.model.game.GameState;

public class PassaBonus extends Azione {

	private Azione bonus;

	/**
	 * this action is used when player wins a nobility bonus but he can't use it. (for examples because he hasn't any build)
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		gameState.getGiocatoreCorrente().getBonusNobiltà().remove(this.bonus);
		gameState.getStato().transizionePassa(gameState);
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new PassaBonusDTO();
	}

	/**
	 * @return the bonus
	 */
	public Azione getBonus() {
		return bonus;
	}

	/**
	 * @param bonus
	 *            the bonus to set
	 */
	public void setBonus(Azione bonus) {
		this.bonus = bonus;
	}

}
