package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.AzioneOffertaParametri;
import common.gameDTO.MarketableDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class AzioneOffertaDTO implements AzioneDTO, AzioneParametri {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -909674011488773839L;
	/**
	 * price for sell the object
	 */
	private int prezzo;
	/**
	 * object that can be sell in the market
	 */
	private MarketableDTO marketableDTO;


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
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);
	}


	/* 
	 * to string 
	 */
	@Override
	public String toString() {
		return "Per fare un'offerta  [Offerta] ";
	}


	/**
	 * @param prezzo the prezzo to set
	 */
	public void setPrezzo(int prezzo) {
		if(prezzo<=0)
			throw new IllegalArgumentException("Il prezzo deve essere maggiore di zero");
		this.prezzo = prezzo;
	}


	/**
	 * @param marketableDTO the marketableDTO to set
	 */
	public void setMarketableDTO(MarketableDTO marketableDTO) {
		if(marketableDTO==null)
			throw new NullPointerException("L'oggetto marketableDTO non può essere null");
		this.marketableDTO = marketableDTO;
	}

	/**
	 * set parameter
	 */
	@Override
	public AzioneOffertaParametri parametri() {
		return new AzioneOffertaParametri(this);
	}

}
