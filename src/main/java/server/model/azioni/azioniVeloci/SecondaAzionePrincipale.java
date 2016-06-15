package server.model.azioni.azioniVeloci;

import java.util.Arrays;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.SecondaAzionePrincipaleDTO;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;

public class SecondaAzionePrincipale extends AzioneVeloce {

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

}