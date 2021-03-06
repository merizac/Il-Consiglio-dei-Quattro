package server.model.azioni.azioniBonus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.BonusTesseraAcquistataNDTO;
import server.model.azioni.Azione;
import server.model.bonus.Bonus;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.TesseraPermesso;
import server.model.notify.AvversarioNotify;
import server.model.notify.GiocatoreNotify;

public class BonusTesseraAcquistataN extends Azione {
	/**
	 * ottieni i bonus di una tessera permesso che hai comprato in precedenza
	 * anche una di quelle a faccia in giù
	 */
	private TesseraPermesso tesseraPermesso;
	private final int id=1;
	/**
	 * use bonus in the selected TesseraPermesso that player already choose, and notify
	 */
	@Override
	public void eseguiAzione(GameState gameState) {

		for (Bonus b : tesseraPermesso.getBonus()) {
			b.usaBonus(gameState);
		}
		gameState.getGiocatoreCorrente().getBonusNobiltà().remove(this);
		List<Giocatore> avversari = new ArrayList<>(gameState.getGiocatori());
		avversari.remove(gameState.getGiocatoreCorrente());
		gameState.notifyObserver(new AvversarioNotify(gameState.getGiocatoreCorrente(), avversari));
		gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(),
				Arrays.asList(gameState.getGiocatoreCorrente())));
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
		if(tesseraPermesso==null)
			throw new NullPointerException("La tessera è null");
		this.tesseraPermesso = tesseraPermesso;
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new BonusTesseraAcquistataNDTO();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusTesseraAcquistataN other = (BonusTesseraAcquistataN) obj;
		if (id != other.id)
			return false;
		return true;
	}	

}
