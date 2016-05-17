package controller;

import java.util.ArrayList;
import java.util.List;

import controller.Partita;
import game.GameState;
import game.azioni.Azione;

public abstract class Stato {

	public List<String> riempiAzioniVeloci(){
		List<String> azioniVeloci = new ArrayList<>();
		azioniVeloci.add("Ingaggiare un aiutante");
		azioniVeloci.add("Cambiare le tessere permesso di costruzione");
		azioniVeloci.add("Mandare un aiutante ad eleggere un consigliere");
		azioniVeloci.add("Compiere un'azione principale");
		return azioniVeloci;
	}
	
	public List<String> riempiAzioniPrincipali(){
		List<String> azioniPrincipali = new ArrayList<>();
		azioniPrincipali.add("Eleggere un consigliere");
		azioniPrincipali.add("Acquistare una tessera permesso di costruzione");
		azioniPrincipali.add("Costruire un emporio usando una tessera permesso");
		azioniPrincipali.add("costruire un emporio con l'aiuto del re");
		return azioniPrincipali;
	}

	public abstract void transizionePrincipale(GameState gameState);
	
	public abstract void transizioneVeloce(GameState gameState);

	public abstract void transizioneSecondaPrincipale(GameState gameState);
	
	public abstract void transizionePescaCarta(GameState gameState);
	
}
