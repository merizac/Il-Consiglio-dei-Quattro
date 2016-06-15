package server.model.azioni;

import java.util.Arrays;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.PescaCartaDTO;
import server.model.game.GameState;
import server.model.notify.GiocatoreNotify;

public class PescaCarta extends Azione {
	
	@Override
	public void eseguiAzione(GameState gameState){
		gameState.getGiocatoreCorrente().getCartePolitica().add(gameState.getMazzoCartePolitica().pescaCarte());
		gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(), 
				Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.getStato().transizionePescaCarta(gameState);
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new PescaCartaDTO();
	}

}
