package server.model.azioni;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.ExitDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.GiocatoreDisconnessoNotify;

public class Exit extends Azione {

	private Giocatore giocatore;

	/**
	 * when a player request the exit action, he is going out of the game.
	 * this method remove the player from the list of players and add in GiocatoriFinePartita
	 * notify the disconnession of the player
	 * set transition in state pattern
	 */
	@Override
	public void eseguiAzione(GameState gameState){
		gameState.getGiocatori().remove(giocatore);
		gameState.getGiocatoriFinePartita().add(giocatore);
		gameState.notifyObserver(new GiocatoreDisconnessoNotify(giocatore, gameState.getGiocatori()));
		if (giocatore.equals(gameState.getGiocatoreCorrente())) {
			gameState.getStato().transizioneExit(gameState);
		}
	}

	/**
	 * @return DTO action
	 */
	@Override
	public AzioneDTO getAzioneDTO() {
		return new ExitDTO();
	}

	/**
	 * @param giocatore
	 *            the giocatore to set
	 */
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	/**
	 * @return the giocatore
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}

}
