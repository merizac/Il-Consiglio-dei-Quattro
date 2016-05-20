package game.query;

import game.GameState;
import game.notify.Notify;

public class GetConsiglieri extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7428453177132003398L;

	@Override
	public Notify perform(GameState gameState) {
		return new ConsiglieriNotify(gameState.getConsiglieri());
	}

}
