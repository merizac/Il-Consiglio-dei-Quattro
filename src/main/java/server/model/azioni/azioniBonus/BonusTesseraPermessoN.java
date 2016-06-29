package server.model.azioni.azioniBonus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.BonusTesseraPermessoNDTO;
import server.model.azioni.Azione;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.notify.AvversarioNotify;
import server.model.notify.GiocatoreNotify;

public class BonusTesseraPermessoN extends Azione {

	/**
	 * Prendi una tesseraPermesso a faccia in su senza pagarne il costo
	 */
	private Regione regione;
	private TesseraPermesso tesseraScoperta;
	private int id;

	/**
	 * take one tesseraPermesso from the selected region. (so remove that from
	 * the region and add to the tessere of the player
	 * 
	 */
	@Override
	public void eseguiAzione(GameState gameState) {

		regione.getTesserePermessoScoperte().remove(tesseraScoperta);
		gameState.getGiocatoreCorrente().getTesserePermesso().add(tesseraScoperta);
		regione.getTesserePermessoScoperte().add(regione.getMazzoTesserePermesso().pescaCarte());
		gameState.getGiocatoreCorrente().getBonusNobiltà().remove(this);
		List<Giocatore> avversari = new ArrayList<>(gameState.getGiocatori());
		avversari.remove(gameState.getGiocatoreCorrente());
		gameState.notifyObserver(new AvversarioNotify(gameState.getGiocatoreCorrente(), avversari));
		gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(),
				Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.getStato().transizioneBonus(gameState);
	}

	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}

	/**
	 * @param regione
	 *            the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}

	/**
	 * @return the indiceTesseraScoperta
	 */
	public TesseraPermesso getTesseraScoperta() {
		return tesseraScoperta;
	}

	/**
	 * @param indiceTesseraScoperta
	 *            the indiceTesseraScoperta to set
	 */
	public void setTesseraScoperta(TesseraPermesso tesseraScoperta) {
		this.tesseraScoperta = tesseraScoperta;
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new BonusTesseraPermessoNDTO();
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
		BonusTesseraPermessoN other = (BonusTesseraPermessoN) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
