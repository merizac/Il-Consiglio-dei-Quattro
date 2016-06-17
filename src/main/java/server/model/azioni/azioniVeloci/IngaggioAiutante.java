package server.model.azioni.azioniVeloci;

import java.util.Arrays;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import server.model.game.GameState;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.MessageNotify;

public class IngaggioAiutante extends AzioneVeloce {

	/**
	 * the player has to pay 3 money for one aiutante
	 * notify
	 * set transition in state pattern
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		if (!gameState.getGiocatoreCorrente().diminuisciRicchezza(3)) {
			gameState.notifyObserver(new MessageNotify("Errore: i soldi non sono sufficienti",
					Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}

		else {
			gameState.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(1);

			gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
			gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(),
					Arrays.asList(gameState.getGiocatoreCorrente())));
			setStatoTransizioneVeloce(gameState);
		}
	}

	/**
	 * @return DTO action
	 */
	@Override
	public AzioneDTO getAzioneDTO() {
		return new IngaggioAiutanteDTO();
	}

}
