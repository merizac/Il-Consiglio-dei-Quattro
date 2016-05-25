package controller;

import java.util.ArrayList;
import java.util.List;

import game.GameState;
import game.azioni.Azione;

public class Stato10 implements Stato {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4129691255828354834L;

	@Override
	public void transizionePrincipale(GameState gameState) {
		if(!gameState.isBonusAzionePrincipale()){
			gameState.nextPlayer();
			gameState.setStato(new StartEnd(gameState));
		}
		else
			gameState.setBonusAzionePrincipale(false); 
	}

	@Override
	public List<Azione> getAzioni() {
		List<Azione> azioniDisponibili=new ArrayList<>();
		return azioniDisponibili;
	}


}
