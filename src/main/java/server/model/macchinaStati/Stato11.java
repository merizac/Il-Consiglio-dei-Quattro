package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import server.model.azioni.azioniPrincipali.CostruzioneAiutoRe;
import server.model.azioni.azioniPrincipali.CostruzioneTesseraPermesso;
import server.model.azioni.azioniPrincipali.ElezioneConsigliere;
import server.model.azioni.azioniVeloci.CambioTesseraPermesso;
import server.model.azioni.azioniVeloci.ElezioneConsigliereVeloce;
import server.model.azioni.azioniVeloci.IngaggioAiutante;
import server.model.azioni.azioniVeloci.SecondaAzionePrincipale;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;
import utility.Utils;

public class Stato11 implements Stato {

	private List<Azione> azioni;

	/**
	 * state with one main action and one quick action
	 * 
	 * @param gameState
	 */
	public Stato11(GameState gameState) {

		Utils.print("[SERVER] "+this);
		this.azioni=Arrays.asList(new ElezioneConsigliere(), new AcquistoTesseraPermesso(),
				new CostruzioneTesseraPermesso(), new CostruzioneAiutoRe(), new IngaggioAiutante(), new CambioTesseraPermesso(), 
				new ElezioneConsigliereVeloce());
		gameState.notifyObserver(new MessageNotify("Scegli un'azione principale o un'azione veloce\n", Arrays.asList(gameState.getGiocatoreCorrente()), false));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	/**
	 * when the action is a main action. this transition take in consideration
	 * if the player win a bonus with a main action.
	 */
	@Override
	public void transizionePrincipale(GameState gameState) {
		if (!gameState.isBonusAzionePrincipale()) {
			gameState.setStato(new Stato01(gameState));
		} else {
			gameState.setBonusAzionePrincipale(false);
			gameState.setStato(new Stato11(gameState));
		}
	}

	/**
	 * when the player win a interactive bonus in nobility track
	 */
	@Override
	public void transizioneBonus(GameState gameState) {
		gameState.setStato(new StatoBonus(gameState, this));
	}

	/**
	 * when the action is a quick action
	 */
	@Override
	public void transizioneVeloce(GameState gameState) {
		gameState.setStato(new Stato10(gameState));
	}

	/**
	 * get actions of this state
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
		return "Stato11";
	}

}
