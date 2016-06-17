package server.model.azioni;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.PassaDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.macchinaStati.StatoAcquistoMarket;

public class Passa extends Azione {

	/**
	 * when the player don't want to do the quick action
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		gameState.getStato().transizionePassa(gameState);
	}

	/**
	 * check if layer is the current player
	 */
	@Override
	public boolean isTurno(Giocatore giocatore, GameState gameState) {
		if (gameState.getStato() instanceof StatoAcquistoMarket)
			return giocatore.equals(((StatoAcquistoMarket) gameState.getStato()).getGiocatori().get(0));
		else
			return giocatore.equals(gameState.getGiocatoreCorrente());
	}

	/*
	 * @return DTO action
	 */
	@Override
	public AzioneDTO getAzioneDTO() {
		return new PassaDTO();
	}

}
