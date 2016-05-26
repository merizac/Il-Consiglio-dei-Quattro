package controller;

import java.util.List;

import game.GameState;
import game.azioni.Azione;
import game.azioni.PescaCarta;
import utility.exception.AzioneNonEseguibile;

public class StartEnd implements Stato {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4274723843140699323L;

	public StartEnd(GameState gameState) throws AzioneNonEseguibile {
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
	public List<String> getAzioni() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StartEnd";
	}

	

}
