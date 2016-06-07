package game.azioni;

import java.util.Arrays;

import game.GameState;
import game.notify.GiocatoreNotify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.PescaCartaDTO;
import utility.exception.AzioneNonEseguibile;

public class PescaCarta extends Azione {

	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		gameState.getGiocatoreCorrente().getCartePolitica().add(gameState.getMazzoCartePolitica().pescaCarte());
		System.out.println("azione pesca carta");
		System.out.println(gameState.getStato());
		gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(), 
				Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.getStato().transizionePescaCarta(gameState);
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new PescaCartaDTO();
	}

}
