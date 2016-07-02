package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;
import server.model.azioni.Azione;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;

public class StatoBonus implements Stato {
	private Stato stato;
	private List<Azione> azioni;

	/**
	 * state with interactive bonus in nobility track
	 * 
	 * @param stato
	 */
	public StatoBonus(GameState gameState, Stato stato) {
		System.out.println("[SERVER] " + this);
		if (stato == null)
			throw new NullPointerException("Lo stato deve essere lo stato precedente");
		this.stato = stato;
		this.azioni = gameState.getGiocatoreCorrente().getBonusNobiltà();
		gameState.notifyObserver(
				new AzioniNotify(Arrays.asList(gameState.getGiocatoreCorrente().getBonusNobiltà().get(0)),
						Arrays.asList(gameState.getGiocatoreCorrente())));

	}

	/**
	 * when the player has won more then one interactive bonus in nobility track
	 */
	@Override
	public void transizioneBonus(GameState gameState) {
		if (gameState.getGiocatoreCorrente().getBonusNobiltà().isEmpty()) {
			stato.transizionePrincipale(gameState);
		} else {
			gameState.notifyObserver(
					new AzioniNotify(Arrays.asList(gameState.getGiocatoreCorrente().getBonusNobiltà().get(0)),
							Arrays.asList(gameState.getGiocatoreCorrente())));
			gameState.setStato(this);
		}
	}

	/**
	 * when the player has no more nobility bonus. return to the state where the
	 * player was before win bonus
	 */
	@Override
	public void transizionePassa(GameState gameState) {
		this.transizioneBonus(gameState);
	}

	/**
	 * get actions
	 */
	@Override
	public List<Azione> getAzioni() {
		return azioni;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stato Bonus";
	}

}
