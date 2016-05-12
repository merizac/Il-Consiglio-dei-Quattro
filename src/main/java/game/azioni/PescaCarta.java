package game.azioni;

import game.GameState;

public class PescaCarta implements Azione{

	private final GameState gameState;
	
	
	/**
	 * @param gameState
	 */
	public PescaCarta(GameState gameState) {
		this.gameState = gameState;
	}


	@Override
	public boolean eseguiAzione() {
		this.gameState.getGiocatoreCorrente().getCartePolitica().add(this.gameState.getMazzoCartePolitica().pescaCarte());
		return true;
	}
	
	

}
