package game.azioni;

import game.GameState;
import game.market.Marketable;
import game.market.Offerta;
import utility.exception.AzioneNonEseguibile;

public class AzioneOfferta extends Azione {
	
	private Marketable marketable;
	private int prezzo;



	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		if(!marketable.possiede(gameState.getGiocatoreCorrente()))
			throw new AzioneNonEseguibile("Questa offerta non è effettuabile");
		else{
			gameState.getOfferteMarket().add(new Offerta(gameState.getGiocatoreCorrente(), marketable, prezzo));
			gameState.getStato().transizioneOfferta(gameState);
		}
	}


	/**
	 * @param marketable the marketable to set
	 */
	public void setMarketable(Marketable marketable) {
		this.marketable = marketable;
	}



	/**
	 * @param prezzo the prezzo to set
	 */
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

}
