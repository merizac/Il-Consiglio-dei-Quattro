package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.PescaCarta;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;

public class StartEnd implements Stato {

	private List<Azione> azioni;

	public StartEnd(GameState gameState) {
		System.out.println("[SERVER] " + this);
		azioni = Arrays.asList(new PescaCarta());
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	@Override
	public void transizionePescaCarta(GameState gameState) {
		gameState.setStato(new Stato11(gameState));

	}

	@Override
	public List<Azione> getAzioni() {
		return this.azioni;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StartEnd";
	}

}
