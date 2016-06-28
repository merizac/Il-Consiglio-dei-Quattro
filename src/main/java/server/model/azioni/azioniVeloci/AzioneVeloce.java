package server.model.azioni.azioniVeloci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.AvversarioNotify;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;

public abstract class AzioneVeloce extends Azione {

	/**
	 * set quick transition in state pattern
	 * 
	 * @param gameState
	 */
	public void setStatoTransizioneVeloce(GameState gameState) {
		gameState.getStato().transizioneVeloce(gameState);
	}

	/**
	 * set second principal action in state pattern
	 * 
	 * @param gameState
	 */
	public void setStatoTransizioneSecondaPrincipale(GameState gameState) {
		gameState.getStato().transizioneSecondaPrincipale(gameState);
	}
	
	public void notify(GameState gameState){
		gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
		List<Giocatore> avversari=new ArrayList<>(gameState.getGiocatori());
		avversari.remove(gameState.getGiocatoreCorrente());
		gameState.notifyObserver(new AvversarioNotify(gameState.getGiocatoreCorrente(), avversari));
		gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

}
