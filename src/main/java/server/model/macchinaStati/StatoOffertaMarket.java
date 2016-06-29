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

public class StatoOffertaMarket implements Stato {

	private List<Azione> azioni;

	public StatoOffertaMarket(GameState gameState) {
		this.azioni = Arrays.asList(new AzioneOfferta(), new Passa());
		System.out.println("[SERVER] Azioni Stato Offerta: " + azioni);
		for (Giocatore g : gameState.getGiocatori()) {
			gameState.notifyObserver(new GiocatoreMarketNotify(g, Arrays.asList(g)));
		}
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.notifyObserver(
				new MessageNotify("Vuoi fare un offerta o passare?\n", Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	@Override
	public void transizionePassa(GameState gameState) {
		
		gameState.decrementaTurno();
		gameState.nextPlayer();
		
		if (gameState.getNumeroTurni() != 0) {
			gameState.notifyObserver(
					new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
			gameState.notifyObserver(
					new MessageNotify("Vuoi fare un offerta o passare?\n", Arrays.asList(gameState.getGiocatoreCorrente())));
			gameState.setStato(this);

		} else {
			if (gameState.getOfferteMarket().isEmpty()) {
				gameState.notifyObserver(new MarketNotify(gameState.getGiocatori(), true));
				gameState.setStato(new StartEnd(gameState));
			} else
				gameState.setStato(new StatoAcquistoMarket(gameState));
		}
	}

	@Override
	public void transizioneOfferta(GameState gameState) {
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), gameState.getGiocatori()));
		gameState.notifyObserver(
				new MessageNotify("Vuoi fare un offerta o passare?\n", Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.setStato(this);
	}

	@Override
	public List<Azione> getAzioni() {
		return this.azioni;
	}

}
