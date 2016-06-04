package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.notify.AzioniNotify;

public class StartEnd implements Stato {


	public StartEnd(GameState gameState) {
			gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	@Override
	public void transizionePescaCarta(GameState gameState) {
		gameState.setStato(new Stato11(gameState));

	}

	@Override
	public List<String> getAzioni() {
		List<String> azioni = new ArrayList<>();
		azioni.add("Pesca[Pesca]");
		return azioni;
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
