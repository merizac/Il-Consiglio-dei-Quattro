package server.model.azioni;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.PassaDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.macchinaStati.StatoAcquistoMarket;

public class Passa extends Azione {

	@Override
	public void eseguiAzione(GameState gameState) {
		gameState.getStato().transizionePassa(gameState);
		System.out.println("Stato :" + gameState.getStato());
	}

	@Override
	public boolean isTurno(Giocatore giocatore, GameState gameState) {
		System.out.println("entrato");
		if (gameState.getStato() instanceof StatoAcquistoMarket)
			return giocatore.equals(((StatoAcquistoMarket) gameState.getStato()).getGiocatori().get(0));
		else
			return giocatore.equals(gameState.getGiocatoreCorrente());
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new PassaDTO();
	}

}
