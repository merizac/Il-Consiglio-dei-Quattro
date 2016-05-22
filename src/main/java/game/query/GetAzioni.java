package game.query;

import game.GameState;


public class GetAzioni extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3075004846258531332L;

	@Override
	public AzioniNotify perform(GameState gameState) {
		return new AzioniNotify(gameState.getStato().getAzioni());
	}

}
