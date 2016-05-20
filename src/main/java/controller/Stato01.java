package controller;

import java.util.ArrayList;
import java.util.List;

import game.GameState;
import game.azioni.Azione;
import game.azioni.ElezioneConsigliere;

public class Stato01 implements Stato {
	

	public Stato01() {

		
	}

	@Override
	public void transizioneVeloce(GameState gameState) {
		gameState.nextPlayer();
		gameState.setStato(new StartEnd(gameState));
	}

	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		gameState.setStato(new Stato10());
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stato01";
	}

	@Override
	public List<Azione> getAzioni() {
		List<Azione> azioniDisponibili=new ArrayList<>();
		return azioniDisponibili;
	}
	

	
}
