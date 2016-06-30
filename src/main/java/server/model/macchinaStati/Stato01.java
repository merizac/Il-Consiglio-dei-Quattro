package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

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

public class Stato01 implements Stato {

	private List<Azione> azioni;

	public Stato01(GameState gameState) {
		System.out.println("[SERVER] " + this);
		azioni = Arrays.asList(new IngaggioAiutante(), new CambioTesseraPermesso(), new ElezioneConsigliereVeloce(),
				new SecondaAzionePrincipale(), new Passa());
		gameState.notifyObserver(new MessageNotify("Scegli un'azione veloce\n", Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));

	}

	@Override
	public void transizioneVeloce(GameState gameState) {
		if (gameState.isUltimoGiro()) {
			if (!gameState.lastNextPlayer()) {

				gameState.setStato(new StartEnd(gameState));
			} else {
				gameState.calcolaVincitore();
			}

		} else {
			gameState.nextPlayer();
			gameState.prossimoTurno();
			System.out.println("[SERVER] numero turno 01: " + gameState.getNumeroTurni());
			if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
				gameState.setStato(new StartEnd(gameState));
			else {
				gameState.notifyObserver(new MarketNotify(gameState.getGiocatori(), false));
				gameState.setStato(new StatoOffertaMarket(gameState));

			}
		}

	}

	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		gameState.setStato(new Stato10(gameState));

	}

	@Override
	public void transizionePassa(GameState gameState) {
		this.transizioneVeloce(gameState);
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
		return "Stato01";
	}

}
