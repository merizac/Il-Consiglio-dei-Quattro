package common.gameDTO;

import server.model.game.Aiutante;
import server.model.game.Giocatore;
import server.model.market.Marketable;

public class AiutanteDTO implements MarketableDTO {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 6485385404330979479L;
	/**
	 * number of helpers
	 */
	private int aiutanti;

	/**
	 * @param aiutanti
	 */
	public AiutanteDTO(int aiutanti) {
		if (aiutanti <= 0)
			throw new IllegalArgumentException("Il numero di aiutanti deve essere positivo");
		this.aiutanti = aiutanti;
	}

	public AiutanteDTO() {
		super();
	}

	/**
	 * @return the aiutanti
	 */
	public int getAiutanti() {
		return aiutanti;
	}

	/**
	 * @param aiutanti
	 *            the aiutanti to set
	 */
	public void setAiutanti(int aiutanti) {
		this.aiutanti = aiutanti;
	}

	/**
	 * return the marketable object refered to the aiutanteDTO
	 * 
	 * @param giocatore
	 */
	@Override
	public Aiutante creaMarketable(Giocatore giocatore) {
		return new Aiutante(this.aiutanti);
	}

	/**
	 * create the object marketableDTO from a marketable
	 */
	@Override
	public void creaMarketableDTO(Marketable marketable) {
		Aiutante aiutante = (Aiutante) marketable;
		this.inizializza(aiutante);

	}

	/**
	 * map aiutante to aiutanteDTO
	 * 
	 * @param aiutante
	 */
	public void inizializza(Aiutante aiutante) {
		this.setAiutanti(aiutante.getAiutante());
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "Aiutante [" + aiutanti + "]";
	}

}
