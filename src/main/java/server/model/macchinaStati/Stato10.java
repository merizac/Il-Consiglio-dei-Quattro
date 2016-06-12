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
import server.model.notify.MessageNotify;

public class Stato10 implements Stato {

	private List<Azione> azioni;

	public Stato10(GameState gameState) {
		System.out.println("[SERVER] " + this);
		azioni = Arrays.asList(new ElezioneConsigliere(), new AcquistoTesseraPermesso(),
				new CostruzioneTesseraPermesso(), new CostruzioneAiutoRe());
		gameState.notifyObserver(
				new MessageNotify("AZIONI PRINCIPALI", Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

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
				System.out.println("[SERVER] numero turno 10: " + gameState.getNumeroTurni());
				gameState.prossimoTurno();

				if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
					gameState.setStato(new StartEnd(gameState));
				else
					gameState.setStato(new StatoOffertaMarket(gameState));
			}
		} else {
			gameState.setBonusAzionePrincipale(false);
			gameState.setStato(new Stato10(gameState));
		}
	}

	@Override
	public void transizioneBonus(GameState gameState) {
		gameState.setStato(new StatoBonus(gameState, this));
	}

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
