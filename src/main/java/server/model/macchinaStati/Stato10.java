package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import server.model.azioni.azioniPrincipali.CostruzioneAiutoRe;
import server.model.azioni.azioniPrincipali.CostruzioneTesseraPermesso;
import server.model.azioni.azioniPrincipali.ElezioneConsigliere;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;
import server.model.notify.MarketNotify;
import server.model.notify.MessageNotify;
import utility.Utils;

public class Stato10 implements Stato {

	private List<Azione> azioni;

	/**
	 * state with one main action and zero quick action
	 * 
	 * @param gameState
	 */
	public Stato10(GameState gameState) {
		Utils.print("[SERVER] " + this);
		azioni = Arrays.asList(new ElezioneConsigliere(), new AcquistoTesseraPermesso(),
				new CostruzioneTesseraPermesso(), new CostruzioneAiutoRe());
		gameState.notifyObserver(
				new MessageNotify("Scegli un'azione principale\n", Arrays.asList(gameState.getGiocatoreCorrente()), false));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	/**
	 * when the action is a main action this transition change if player win
	 * bonus of addicional main action, if is the last round (someone finish
	 * emporium) , or if it's market time.
	 */
	@Override
	public void transizionePrincipale(GameState gameState) {
		if (!gameState.isBonusAzionePrincipale()) {
			if (gameState.isUltimoGiro()) {
				if (gameState.lastNextPlayer())
					gameState.calcolaVincitore();
				else {
					gameState.nextPlayer();
					gameState.setStato(new StartEnd(gameState));
				}
			} else {
				gameState.nextPlayer();
				Utils.print("[SERVER] numero turno 10: " + gameState.getNumeroTurni());
				gameState.prossimoTurno();

				if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
					gameState.setStato(new StartEnd(gameState));
				else {
					gameState.notifyObserver(new MarketNotify(gameState.getGiocatori(), false));
					gameState.setStato(new StatoOffertaMarket(gameState));
				}
			}
		} else {
			gameState.setBonusAzionePrincipale(false);
			gameState.setStato(new Stato10(gameState));
		}
	}

	/**
	 * if the player win a interactive bonus in nobility track
	 */
	@Override
	public void transizioneBonus(GameState gameState) {
		gameState.setStato(new StatoBonus(gameState, this));
	}

	/**
	 * get azioni
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
		return "Stato10";
	}

}
