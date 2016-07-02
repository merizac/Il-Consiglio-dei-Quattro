package server.model.azioni.azioniVeloci;

import java.util.Arrays;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import server.model.game.GameState;
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
					Arrays.asList(gameState.getGiocatoreCorrente()), false));
			return;
		}

		else {
			gameState.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(1);
			notify(gameState);
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
