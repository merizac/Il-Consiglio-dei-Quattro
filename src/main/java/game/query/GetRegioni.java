package game.query;

import game.GameState;

public class GetRegioni extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6358422691361897684L;

	@Override
	public RegioniNotify perform(GameState gameState) {
		return new RegioniNotify(gameState.getRegioni());
	}

}
