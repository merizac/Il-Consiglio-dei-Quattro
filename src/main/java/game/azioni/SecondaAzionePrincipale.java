package game.azioni;

import java.util.ArrayList;
import game.GameState;

public class SecondaAzionePrincipale extends AzioneVeloce {

	public SecondaAzionePrincipale(GameState gameState) {
		super(gameState);
		// TODO Auto-generated constructor stub
	}

	/**
	 * if the player has already do the main action, fill the Array of Main Action 
	 * 			of the player for do another main action
	 * if the player is doing for the first time his action, and he choose first Speed Action, 
	 * 		at the end of the (second) main action, i have to refill the Array of Main Action
	 * @return false if it is not possible do the action for some reason
	 */
	@Override
	public boolean eseguiAzione() {
		ArrayList<AzionePrincipale> azionePrincipale = this.gameState.getGiocatoreCorrente().getAzioniPrincipali();
		/*this.gameState.svuotaAzioniVeloci();
		if (azionePrincipale.isEmpty()){
		    this.gameState.riempiAzioniPrincipali();
			}
		
		//this.gameState.getView().scegliAzionePrincipale();
		//AZIONE PRINCIPALE: deve eseguire l'azione principale , controllare il ritorno e se andato 
		// a buon fine svuotare azione principale
			
		if (!azionePrincipale.isEmpty()){
			this.gameState.riempiAzioniPrincipali();
			}*/
		
		setStatoTransizioneSecondaPrincipale(); 
		return true;
	}
}
