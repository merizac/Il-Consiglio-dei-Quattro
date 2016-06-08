package game.azioni;

import game.GameState;
import game.market.Marketable;
import game.market.Offerta;
import gameDTO.azioniDTO.AzioneDTO;
import utility.exception.AzioneNonEseguibile;

public class AzioneOfferta extends Azione {
	
	private Marketable marketable;
	private int prezzo;



	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		if(!marketable.possiede(gameState.getGiocatoreCorrente()))
			throw new AzioneNonEseguibile("Questa offerta non è effettuabile");
		else{
			System.out.println(marketable);
			System.out.println(prezzo);
			gameState.getOfferteMarket().add(new Offerta(gameState.getGiocatoreCorrente(), marketable, prezzo));
			System.out.println(gameState.getStato());
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


	@Override
	public AzioneDTO getAzioneDTO() {
		return null;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fai un'offerta  [Offerta]";
	}

}
