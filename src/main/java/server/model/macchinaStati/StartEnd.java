package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.PescaCarta;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;
import utility.Utils;

public class StartEnd implements Stato {

	/**
	 * list of action available in this state
	 */
	private List<Azione> azioni;

	/**
	 * start end state 
	 * the only action that the player can do is pick politic card
	 * @param gameState
	 */
	public StartEnd(GameState gameState) {
		Utils.print("[SERVER] " + this);
		gameState.notifyObserver(new MessageNotify("E' il tuo turno "+gameState.getGiocatoreCorrente().getNome()+"\nPesca una carta!\n"
				, Arrays.asList(gameState.getGiocatoreCorrente()), false));
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
	 * get available action
	 */
	@Override
	public List<Azione> getAzioni() {
		return this.azioni;
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "StartEnd";
	}

}
