package game.azioni;

import game.GameState;
import game.market.Offerta;
import utility.exception.AzioneNonEseguibile;

public class AzioneAcquisto extends Azione {
	
	private final Offerta offerta;

	public AzioneAcquisto(GameState gameState, Offerta offerta) {
		super(gameState);
		this.offerta=offerta;
	}

	@Override
	public void eseguiAzione() throws AzioneNonEseguibile {
		if(!offerta.getMarketable().acquista(gameState.getGiocatoreCorrente(), offerta))
			 throw new AzioneNonEseguibile("L'acquisto non è effettuabile");
		else{
			gameState.getStato().transizioneOfferta(gameState);
		}
	}

}
