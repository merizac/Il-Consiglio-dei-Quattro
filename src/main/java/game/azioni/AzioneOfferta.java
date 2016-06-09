package game.azioni;

import game.GameState;
import game.market.Marketable;
import game.market.Offerta;
import gameDTO.azioniDTO.AzioneDTO;
import utility.exception.AzioneNonEseguibile;

public class AzioneOfferta extends Azione {
	
	private final int ID=11;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AzioneOfferta other = (AzioneOfferta) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

}
