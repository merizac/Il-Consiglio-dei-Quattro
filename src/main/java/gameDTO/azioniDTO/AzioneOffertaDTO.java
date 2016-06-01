package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.MarketableDTO;

public class AzioneOffertaDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -909674011488773839L;
	private int prezzo;
	private MarketableDTO marketableDTO;
	
	/**
	 * @param prezzo
	 * @param marketableDTO
	 */
	public AzioneOffertaDTO(MarketableDTO marketableDTO, int prezzo) {
		if(prezzo<=0)
			throw new IllegalArgumentException("Il prezzo deve essere maggiore di zero");
		if(marketableDTO==null)
			throw new NullPointerException("L'oggetto marketableDTO non può essere null");
		this.prezzo = prezzo;
		this.marketableDTO = marketableDTO;
	}

	/**
	 * @return the prezzo
	 */
	public int getPrezzo() {
		return prezzo;
	}

	/**
	 * @return the marketableDTO
	 */
	public MarketableDTO getMarketableDTO() {
		return marketableDTO;
	}

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

}
