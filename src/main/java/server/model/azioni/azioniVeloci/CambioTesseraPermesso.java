package server.model.azioni.azioniVeloci;

import java.util.Arrays;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.CambioTesserePermessoDTO;
import server.model.game.GameState;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.notify.MessageNotify;

public class CambioTesseraPermesso extends AzioneVeloce {

	private Regione regione;

	/**
	 * change tessere permesso in the region which the player had selected
	 * add that cards in the deck and change with other new tessere
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		if (gameState.getGiocatoreCorrente().getAiutanti().getAiutante() > 0) {
			gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(1);
			RimozioneCarte();
			SostituzioneCarte();
			notify(gameState);
			setStatoTransizioneVeloce(gameState);
		} else {
			gameState.notifyObserver(new MessageNotify("Errore: non hai abbastanza aiutanti",
					Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}

	}

	/**
	 * remove cards from TesserePermesseScoperte and add to MazzoTesserePermesso
	 */
	private void RimozioneCarte() {

		int size = regione.getTesserePermessoScoperte().size();
		for (int i = 0; i < size; i++) {
			TesseraPermesso permesso = regione.getTesserePermessoScoperte().remove(0);
			regione.getMazzoTesserePermesso().aggiungiCarta(permesso);
		}
	}

	/**
	 * remove cards from MazzoTesserePermesso and add cards to
	 * TesserePermessoScoperte
	 */
	private void SostituzioneCarte() {

		for (int i = 0; i < 2; i++) {
			TesseraPermesso tessera = regione.getMazzoTesserePermesso().pescaCarte();
			regione.getTesserePermessoScoperte().add(tessera);
		}

	}

	/**
	 * @param regione
	 *            the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}

	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}

	/**
	 * @return the DTO action
	 */
	@Override
	public AzioneDTO getAzioneDTO() {
		return new CambioTesserePermessoDTO();
	}

}
