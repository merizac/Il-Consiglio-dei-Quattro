package server.model.azioni;

import common.azioniDTO.AzioneDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.GiocatoreDisconnessoNotify;

public class Exit extends Azione {

	private Giocatore giocatore;

	@Override
	public void eseguiAzione(GameState gameState){
		gameState.getGiocatori().remove(giocatore);
		gameState.getGiocatoriFinePartita().add(giocatore);
		gameState.notifyObserver(new GiocatoreDisconnessoNotify(giocatore, gameState.getGiocatori()));
		if (giocatore.equals(gameState.getGiocatoreCorrente())) {
			gameState.getStato().transizioneExit(gameState);
		}
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		// TODO Auto-generated method stub
		return null;
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
