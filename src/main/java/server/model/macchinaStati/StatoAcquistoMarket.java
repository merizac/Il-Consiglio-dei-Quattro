package server.model.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.Passa;
import server.model.azioni.azioniMarket.AzioneAcquisto;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.AvversarioNotify;
import server.model.notify.AzioniNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.MarketNotify;
import server.model.notify.MessageNotify;
import server.model.notify.OffertaNotify;
import utility.Utils;

public class StatoAcquistoMarket implements Stato {

	/**
	 * list of player
	 */
	private List<Giocatore> giocatori;
	
	/**
	 * list of action available in this state
	 */
	private List<Azione> azioni;

	/**
	 * state of acquisto in market
	 * 
	 * @param gameState
	 */
	public StatoAcquistoMarket(GameState gameState) {
		Utils.print("[SERVER] " + this);
		this.azioni = Arrays.asList(new AzioneAcquisto(), new Passa());
		this.giocatori = new ArrayList<>(gameState.getGiocatori());
		inizializzaStato(gameState);
	}

	/**
	 * notify to the player of avaiable actions
	 * 
	 * @param gameState
	 */
	private void inizializzaStato(GameState gameState) {
		Collections.shuffle(giocatori);
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(giocatori.get(0))));
		gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), Arrays.asList(giocatori.get(0))));
		gameState.notifyObserver(new MessageNotify("Vuoi acquistare\n?", Arrays.asList(giocatori.get(0)), true));
	}

	/**
	 * when player choose to don't buy anything players in this state are random
	 */
	@Override
	public void transizionePassa(GameState gameState) {
		this.giocatori.remove(0);
		if (!giocatori.isEmpty()) {
			Collections.shuffle(giocatori);
			gameState.setStato(this);
			gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), gameState.getGiocatori()));
			gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(giocatori.get(0))));
			gameState.notifyObserver(new MessageNotify("Vuoi acquistare?\n", Arrays.asList(giocatori.get(0)), true));
		} else {
			gameState.getOfferteMarket().clear();
			gameState.notifyObserver(new MarketNotify(gameState.getGiocatori(), true));
			gameState.setStato(new StartEnd(gameState));
		}
	}

	/**
	 * when the player choose to do an offert
	 */
	@Override
	public void transizioneOfferta(GameState gameState) {
		if (gameState.getOfferteMarket().isEmpty()) {
			gameState.notifyObserver(new MessageNotify("Gli oggetti in vendita sono finiti\n", giocatori, true));
			gameState.notifyObserver(new MarketNotify(gameState.getGiocatori(), true));
			gameState.setStato(new StartEnd(gameState));
		} else {
			gameState.setStato(this);
			gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), Arrays.asList(giocatori.get(0))));
			gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(giocatori.get(0))));
			List<Giocatore> avversari = new ArrayList<>(gameState.getGiocatori());
			avversari.remove(gameState.getGiocatoreCorrente());
			gameState.notifyObserver(new AvversarioNotify(gameState.getGiocatoreCorrente(), avversari));
			gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(),
					Arrays.asList(gameState.getGiocatoreCorrente())));
			gameState.notifyObserver(new MessageNotify("Vuoi acquistare\n?", Arrays.asList(giocatori.get(0)), true));
		}
	}

	/**
	 * get actions
	 */
	@Override
	public List<Azione> getAzioni() {
		return this.azioni;
	}

	/**
	 * @return the giocatori
	 */
	public List<Giocatore> getGiocatori() {
		return giocatori;
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "StatoAcquistoMarket";
	}

}
