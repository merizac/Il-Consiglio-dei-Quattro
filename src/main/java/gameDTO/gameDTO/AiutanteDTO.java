package gameDTO.gameDTO;

import game.Aiutante;
import game.Giocatore;
import game.market.Marketable;

public class AiutanteDTO implements MarketableDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6485385404330979479L;
	private int aiutanti;
	/**
	 * @param aiutanti
	 */
	public AiutanteDTO(int aiutanti) {
		if(aiutanti<=0)
			throw new IllegalArgumentException("Il numero di aiutanti deve essere positivo");
		this.aiutanti = aiutanti;
	}
	/**
	 * @return the aiutanti
	 */
	public int getAiutanti() {
		return aiutanti;
	}
	/**
	 * @param aiutanti the aiutanti to set
	 */
	public void setAiutanti(int aiutanti) {
		this.aiutanti = aiutanti;
	}
	@Override
	public Marketable creaMarketable(Giocatore giocatore) {
		return new Aiutante(this.aiutanti);
	}


}
