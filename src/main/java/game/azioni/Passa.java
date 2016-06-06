package game.azioni;

import game.GameState;
import game.Giocatore;
import game.macchinaStati.StatoAcquistoMarket;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.PassaDTO;
import utility.exception.AzioneNonEseguibile;

public class Passa extends Azione {

	@Override
	public void eseguiAzione(GameState gameState) {
		try {
			gameState.getStato().transizionePassa(gameState);
			System.out.println("Stato :"+gameState.getStato());
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
