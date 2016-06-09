package game.azioni;

import game.GameState;
import game.Giocatore;
import game.notify.GiocatoreDisconnessoNotify;
import gameDTO.azioniDTO.AzioneDTO;
import utility.exception.AzioneNonEseguibile;

public class Exit extends Azione{
	
	private Giocatore giocatore;

	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		gameState.getGiocatori().remove(giocatore);
		gameState.getGiocatoriFinePartita().add(giocatore);
		gameState.notifyObserver(new GiocatoreDisconnessoNotify(giocatore, gameState.getGiocatori()));
		gameState.getStato().transizioneExit(gameState);
		System.out.println(gameState.getStato());
		
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param giocatore the giocatore to set
	 */
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

}
