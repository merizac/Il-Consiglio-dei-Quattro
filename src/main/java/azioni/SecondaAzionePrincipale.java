package azioni;

import java.util.ArrayList;

import game.Partita;

public class SecondaAzionePrincipale extends AzioneVeloce {

	public SecondaAzionePrincipale(Partita partita) {
		super(partita);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean eseguiAzione() {
		ArrayList<AzionePrincipale> azionePrincipale = this.partita.getGiocatoreCorrente().getAzioniPrincipali();
		this.partita.svuotaAzioniVeloci();
		if (azionePrincipale.isEmpty()){
		/*	this.partita.riempiAzioniPrincipali();*/
			}
		
			this.partita.getView().scegliAzionePrincipale();
			//AZIONE PRINCIPALE: deve eseguire l'azione principale , controllare il ritorno e se andato 
			// a buon fine svuotare azione principale
			
		if (!azionePrincipale.isEmpty()){
		/*	this.partita.riempiAzioniPrincipali();*/
			}
		
		
		return false;
	}
}
