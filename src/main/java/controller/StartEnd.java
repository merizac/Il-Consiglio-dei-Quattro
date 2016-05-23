package controller;

import java.util.List;

import game.GameState;
import game.azioni.Azione;

public class StartEnd implements Stato {


	public StartEnd(GameState gameState) {
		if(gameState.getNumeroTurni()!=gameState.getGiocatori().size()){
			gameState.prossimoTurno();
		}
		else
			gameState.setStato(new StatoOffertaMarket());
	}

	@Override
	public void transizionePescaCarta(GameState gameState) {
		gameState.setStato(new Stato11());
		
	}

	@Override
	public List<Azione> getAzioni() {
		// TODO Auto-generated method stub
		return null;
	}

}
