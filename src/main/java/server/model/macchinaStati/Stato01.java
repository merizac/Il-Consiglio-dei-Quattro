package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.Server;
import server.model.azioni.Azione;
import server.model.azioni.Passa;
import server.model.azioni.azioniVeloci.CambioTesseraPermesso;
import server.model.azioni.azioniVeloci.ElezioneConsigliereVeloce;
import server.model.azioni.azioniVeloci.IngaggioAiutante;
import server.model.azioni.azioniVeloci.SecondaAzionePrincipale;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;
import server.model.notify.MarketNotify;
import server.model.notify.MessageNotify;
import utility.Utils;

public class Stato01 implements Stato {

	/**
	 * list of action available in this state
	 */
	private List<Azione> azioni;

	/**
	 * state with zero main action and one quick action
	 * 
	 * @param gameState
	 */
	public Stato01(GameState gameState) {
		Utils.print("[SERVER] " + this);
		azioni = Arrays.asList(new IngaggioAiutante(), new CambioTesseraPermesso(), new ElezioneConsigliereVeloce(),
				new SecondaAzionePrincipale(), new Passa());
		gameState.notifyObserver(
				new MessageNotify("Scegli un'azione veloce\n", Arrays.asList(gameState.getGiocatoreCorrente()), false));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));

	}

	/**
	 * quick transition called by a quick action
	 * 
	 */
	@Override
	public void transizioneVeloce(GameState gameState) {
		if (gameState.isUltimoGiro()) {
			if (!gameState.lastNextPlayer()) {

				gameState.setStato(new StartEnd(gameState));
			} else {
				gameState.calcolaVincitore();
				Server.disconnettiClient(gameState);
			}

		} else {
			gameState.nextPlayer();
			gameState.prossimoTurno();
			Utils.print("[SERVER] numero turno 01: " + gameState.getNumeroTurni());
			if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
				gameState.setStato(new StartEnd(gameState));
			else {
				gameState.notifyObserver(new MarketNotify(gameState.getGiocatori(), false));
				gameState.setStato(new StatoOffertaMarket(gameState));

			}
		}

	}

	/**
	 * transition called when the action is the quick action with second main
	 * action
	 */
	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		gameState.setStato(new Stato10(gameState));

	}

	/**
	 * when the player skip quick action
	 */
	@Override
	public void transizionePassa(GameState gameState) {
		this.transizioneVeloce(gameState);
	}

	/**
	 * get actions
	 */
	@Override
	public List<Azione> getAzioni() {
		return this.azioni;
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "Stato01";
	}

}
