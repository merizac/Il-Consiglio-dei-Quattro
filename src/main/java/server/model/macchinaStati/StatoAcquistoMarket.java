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

public class StatoAcquistoMarket implements Stato {

	private List<Giocatore> giocatori;
	private List<Azione> azioni;

	public StatoAcquistoMarket(GameState gameState) {
		System.out.println("[SERVER] " + this);
		this.azioni = Arrays.asList(new AzioneAcquisto(), new Passa());
		this.giocatori = new ArrayList<>(gameState.getGiocatori());
		inizializzaStato(gameState);
	}

	private void inizializzaStato(GameState gameState) {
		Collections.shuffle(giocatori);
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(giocatori.get(0))));
		gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), Arrays.asList(giocatori.get(0))));
		gameState.notifyObserver(new MessageNotify("Vuoi acquistare\n?", Arrays.asList(giocatori.get(0))));
	}

	@Override
	public void transizionePassa(GameState gameState) {
		this.giocatori.remove(0);
		if (!giocatori.isEmpty()) {
			Collections.shuffle(giocatori);
			gameState.setStato(this);
			gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), gameState.getGiocatori()));
			gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(giocatori.get(0))));
		} else {
			gameState.getOfferteMarket().clear();
			gameState.notifyObserver(new MarketNotify(gameState.getGiocatori(), true));
			gameState.setStato(new StartEnd(gameState));
		}
	}

	public void transizioneOfferta(GameState gameState) {
		if (gameState.getOfferteMarket().isEmpty()) {
			gameState.notifyObserver(new MessageNotify("Gli oggetti in vendita sono finiti\n", giocatori));
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
			gameState.notifyObserver(new MessageNotify("Vuoi acquistare\n?", Arrays.asList(giocatori.get(0))));
		}
	}

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
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatoAcquistoMarket";
	}

}
