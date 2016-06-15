package server.model.azioni.azioniBonus;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.BonusTesseraPermessoNDTO;
import server.model.azioni.Azione;
import server.model.game.GameState;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;

public class BonusTesseraPermessoN extends Azione {

	/**
	 * Prendi una tesseraPermesso a faccia in su senza pagarne il costo
	 */
	private Regione regione;
	private TesseraPermesso tesseraScoperta;

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

}
