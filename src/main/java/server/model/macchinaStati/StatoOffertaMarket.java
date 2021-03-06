package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.Passa;
import server.model.azioni.azioniMarket.AzioneOfferta;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.AzioniNotify;
import server.model.notify.GiocatoreMarketNotify;
import server.model.notify.MarketNotify;
import server.model.notify.MessageNotify;
import server.model.notify.OffertaNotify;
import utility.Utils;

public class StatoOffertaMarket implements Stato {

	/**
	 * list of action available in this state
	 */
	private List<Azione> azioni;

	/**
	 * market state with offer action.
	 * 
	 * @param gameState
	 */
	public StatoOffertaMarket(GameState gameState) {
		this.azioni = Arrays.asList(new AzioneOfferta(), new Passa());
		Utils.print("[SERVER] Azioni Stato Offerta: " + azioni);
		for (Giocatore g : gameState.getGiocatori()) {
			gameState.notifyObserver(new GiocatoreMarketNotify(g, Arrays.asList(g)));
		}
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.notifyObserver(new MessageNotify("Vuoi fare un offerta o passare?\n",
				Arrays.asList(gameState.getGiocatoreCorrente()), true));
	}

	/**
	 * when the player decide to don't do any offers
	 */
	@Override
	public void transizionePassa(GameState gameState) {

		gameState.decrementaTurno();
		gameState.nextPlayer();

		if (gameState.getNumeroTurni() != 0) {
			gameState.notifyObserver(
					new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
			gameState.notifyObserver(new MessageNotify("Vuoi fare un offerta o passare?\n",
					Arrays.asList(gameState.getGiocatoreCorrente()), true));
			gameState.setStato(this);

		} else {
			if (gameState.getOfferteMarket().isEmpty()) {
				gameState.notifyObserver(new MarketNotify(gameState.getGiocatori(), true));
				gameState.setStato(new StartEnd(gameState));
			} else
				gameState.setStato(new StatoAcquistoMarket(gameState));
		}
	}

	/**
	 * when the player decide to offer something
	 */
	@Override
	public void transizioneOfferta(GameState gameState) {
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), gameState.getGiocatori()));
		gameState.notifyObserver(new MessageNotify("Vuoi fare un offerta o passare?\n",
				Arrays.asList(gameState.getGiocatoreCorrente()), true));
		gameState.setStato(this);
	}

	/**
	 * get action avaiable in this state
	 */
	@Override
	public List<Azione> getAzioni() {
		return this.azioni;
	}

}
