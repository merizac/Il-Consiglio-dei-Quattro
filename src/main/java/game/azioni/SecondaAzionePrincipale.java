package game.azioni;

import java.util.Arrays;

import game.GameState;
import game.notify.AzioniNotify;
import game.notify.MessageNotify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.SecondaAzionePrincipaleDTO;

public class SecondaAzionePrincipale extends AzioneVeloce {

	private final int ID=8;

	/**
	 * if the player has already do the main action, fill the Array of Main Action 
	 * 			of the player for do another main action
	 * if the player is doing for the first time his action, and he choose first Speed Action, 
	 * 		at the end of the (second) main action, i have to refill the Array of Main Action
	 * @return false if it is not possible do the action for some reason
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		if(gameState.getGiocatoreCorrente().getAiutanti().getAiutante()>2){
			gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(3);	
			setStatoTransizioneSecondaPrincipale(gameState);
			gameState.notifyObserver(new AzioniNotify(gameState.getStato().getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
		}
		else{
			gameState.notifyObserver(new MessageNotify("Errore: non hai abbastanza aiutanti",
					Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new SecondaAzionePrincipaleDTO();
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
		SecondaAzionePrincipale other = (SecondaAzionePrincipale) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}
