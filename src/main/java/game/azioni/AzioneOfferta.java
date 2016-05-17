package game.azioni;

import game.GameState;
import game.market.Marketable;
import game.market.Offerta;
import utility.exception.AzioneNonEseguibile;

public class AzioneOfferta extends Azione {
	
	private Marketable marketable;
	private int prezzo;

	public AzioneOfferta(GameState gameState, Marketable marketable, int prezzo) {
		super(gameState);
		this.marketable=marketable;
		this.prezzo=prezzo;
	}

	@Override
	public void eseguiAzione() throws AzioneNonEseguibile {
		if(!marketable.possiede(gameState.getGiocatoreCorrente()))
			throw new AzioneNonEseguibile("Questa offerta non è effettuabile");
		else{
			gameState.getOfferteMarket().add(new Offerta(gameState.getGiocatoreCorrente(), marketable, prezzo));
			gameState.getStato().transizioneOfferta(gameState);
		}
	}

}
