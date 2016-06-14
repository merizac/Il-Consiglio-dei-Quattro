package server.model.azioni.azioniBonus;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.BonusTesseraAcquistataNDTO;
import server.model.azioni.Azione;
import server.model.bonus.Bonus;
import server.model.game.GameState;
import server.model.game.TesseraPermesso;

public class BonusTesseraAcquistataN extends Azione {
	/**
	 * ottieni i bonus di una tessera permesso che hai comprato in precedenza
	 * anche una di quelle a faccia in giù
	 */
	private TesseraPermesso tesseraPermesso;

	@Override
	public void eseguiAzione(GameState gameState) {

		for (Bonus b : tesseraPermesso.getBonus()) {
			b.usaBonus(gameState);
		}
		gameState.getGiocatoreCorrente().getBonusNobiltà().remove(this);
		gameState.getStato().transizioneBonus(gameState);

	}

	/**
	 * @return the indiceTesseraPermesso
	 */
	public TesseraPermesso getTesseraPermesso() {
		return tesseraPermesso;
	}

	/**
	 * @param indiceTesseraPermesso
	 *            the indiceTesseraPermesso to set
	 */
	public void setTesseraPermesso(TesseraPermesso tesseraPermesso) {
		this.tesseraPermesso = tesseraPermesso;
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new BonusTesseraAcquistataNDTO();
	}

}
