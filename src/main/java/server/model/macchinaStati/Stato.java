package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.MessageNotify;

public interface Stato {

	/**
	 * transition set by main action
	 * 
	 * @param gameState
	 */
	public default void transizionePrincipale(GameState gameState) {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente()), false));
	}

	/**
	 * transition set by quick action
	 * 
	 * @param gameState
	 */
	public default void transizioneVeloce(GameState gameState) {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente()), false));
	};

	/**
	 * transition set by quick action with additional main action
	 * 
	 * @param gameState
	 */
	public default void transizioneSecondaPrincipale(GameState gameState) {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente()), false));
	};

	/**
	 * transition set by action of pick a politic card
	 * 
	 * @param gameState
	 */
	public default void transizionePescaCarta(GameState gameState) {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente()), false));
	};

	/**
	 * transition set by offer action in market state
	 * 
	 * @param gameState
	 */
	public default void transizioneOfferta(GameState gameState) {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente()), false));
	};

	/**
	 * transition set by skip quick action
	 * 
	 * @param gameState
	 */
	public default void transizionePassa(GameState gameState) {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente()), false));
	};

	/**
	 * transition set by interactive bonus in nobility track
	 * 
	 * @param gameState
	 */
	public default void transizioneBonus(GameState gameState) {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente()), false));
	};

	/**
	 * transition set by exit action
	 * 
	 * @param gameState
	 */
	public default void transizioneExit(GameState gameState) {
		if (gameState.getGiocatori().size() == 1 && !gameState.isUltimoGiro()) {
			Giocatore g = gameState.getGiocatori().remove(0);
			gameState.getGiocatoriFinePartita().add(g);
			gameState.calcolaVincitore();
		} else {
			if (gameState.isUltimoGiro()) {
				if (!gameState.getGiocatori().isEmpty()) {
					gameState.setGiocatoreCorrente(gameState.getGiocatori().get(0));
					gameState.setStato(new StartEnd(gameState));
				} else {
					gameState.calcolaVincitore();
				}

			} else {
				gameState.setGiocatoreCorrente(gameState.getGiocatori().get(0));
				if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
					gameState.setStato(new StartEnd(gameState));
				else
					gameState.setStato(new StatoOffertaMarket(gameState));
			}
		}
	}

	/**
	 * get action
	 * 
	 * @return
	 */
	public List<Azione> getAzioni();

	/**
	 * 
	 * @param azioniStato
	 * @param azione
	 * @return true if the action can be execute, false otherwise
	 */
	public default boolean daEseguire(List<Azione> azioniStato, Azione azione) {
		for (Azione a : azioniStato) {
			if (azione.getClass().equals(a.getClass()))
				return true;
		}
		return false;
	}

}
