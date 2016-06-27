package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.Exit;
import server.model.azioni.PescaCarta;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;

public class StartEnd implements Stato {

	private List<Azione> azioni;

	/**
	 * start end state 
	 * the only action that the player can do is pick politic card
	 * @param gameState
	 */
	public StartEnd(GameState gameState) {
		System.out.println("[SERVER] " + this);
		gameState.notifyObserver(new MessageNotify(gameState.getGiocatoreCorrente().getNome()+ " é il tuo turno!\n"
				, Arrays.asList(gameState.getGiocatoreCorrente())));
		azioni = Arrays.asList(new PescaCarta());
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	/**
	 * transition when player pick a politic card
	 */
	@Override
	public void transizionePescaCarta(GameState gameState) {
		gameState.setStato(new Stato11(gameState));

	}
	
	/**
	 * get avaiable action
	 */
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
