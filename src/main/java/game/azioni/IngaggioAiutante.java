package game.azioni;

import java.util.Arrays;

import game.GameState;
import game.notify.MessageNotify;
import game.notify.GameStateNotify;
import game.notify.GiocatoreNotify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.IngaggioAiutanteDTO;

public class IngaggioAiutante extends AzioneVeloce {

	private final int ID=5;
	/**
	 * @return true if the player has enough money, and add one aiutante
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		if(!gameState.getGiocatoreCorrente().diminuisciRicchezza(3)){
			gameState.notifyObserver(new MessageNotify("Errore: i soldi non sono sufficienti",
					Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}
		
		else {
			gameState.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(1);
			
			gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
			gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(),
					Arrays.asList(gameState.getGiocatoreCorrente())));
			setStatoTransizioneVeloce(gameState); 
			}	
		}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new IngaggioAiutanteDTO();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngaggioAiutante other = (IngaggioAiutante) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}
