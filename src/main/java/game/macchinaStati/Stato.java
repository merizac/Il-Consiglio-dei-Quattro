package game.macchinaStati;

import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.notify.MessageNotify;
import server.Server;
import utility.exception.AzioneNonEseguibile;

public interface Stato {

	public default void transizionePrincipale(GameState gameState) throws AzioneNonEseguibile {
		// throw new AzioneNonEseguibile("Il tipo di azione non può essere
		// eseguita!");
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	public default void transizioneVeloce(GameState gameState) throws AzioneNonEseguibile {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};

	public default void transizioneSecondaPrincipale(GameState gameState) throws AzioneNonEseguibile {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};

	public default void transizionePescaCarta(GameState gameState) throws AzioneNonEseguibile {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};

	public default void transizioneOfferta(GameState gameState) throws AzioneNonEseguibile {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};

	public default void transizionePassa(GameState gameState) throws AzioneNonEseguibile {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};

	public default void transizioneBonus(GameState gameState) throws AzioneNonEseguibile {
		gameState.notifyObserver(
				new MessageNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};

	public default void transizioneExit(GameState gameState) {
		if (gameState.getGiocatori().size()==1) {
			Giocatore g=gameState.getGiocatori().remove(0);
			gameState.getGiocatoriFinePartita().add(g);
			gameState.setStato(new StatoFinePartita(gameState));
		} else {
			if (gameState.isUltimoGiro()) {
				if (!gameState.lastNextPlayer()) {

					gameState.setStato(new StartEnd(gameState));
				} else {
					gameState.setStato(new StatoFinePartita(gameState));
				}

			} else {
				gameState.nextPlayer();
				gameState.prossimoTurno();
				if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
					gameState.setStato(new StartEnd(gameState));
				else
					gameState.setStato(new StatoOffertaMarket(gameState));
			}
		}
	}

	public List<Azione> getAzioni();

}
