package game.azioni;

import game.GameState;
import game.notify.AzioniNotify;
import game.notify.GiocatoreDTONotify;
import utility.exception.AzioneNonEseguibile;

public class PescaCarta extends Azione {

	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		gameState.getGiocatoreCorrente().getCartePolitica().add(gameState.getMazzoCartePolitica().pescaCarte());
		System.out.println("azione pesca carta");
		System.out.println(gameState.getStato());
		gameState.notifyObserver(new GiocatoreDTONotify(gameState.getGiocatoreCorrente()));
		gameState.getStato().transizionePescaCarta(gameState);
	}

}
