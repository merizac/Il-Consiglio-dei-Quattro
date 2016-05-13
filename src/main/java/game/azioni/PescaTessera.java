package game.azioni;

import game.GameState;

public class PescaTessera extends Azione {

	public PescaTessera(GameState gameState) {
		super(gameState);
	}

	@Override
	public void eseguiAzione() {
		gameState.getStato().transizionePescaCarta(gameState);
	}

}
