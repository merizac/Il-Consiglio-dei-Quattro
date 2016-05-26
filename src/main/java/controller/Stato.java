package controller;

import java.io.Serializable;
import java.util.List;
import game.GameState;
import game.azioni.Azione;
import game.notify.ErrorNotify;
import utility.exception.AzioneNonEseguibile;

public interface Stato extends Serializable{

	/*public List<String> riempiAzioniVeloci(){
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
	}*/

	public default void transizionePrincipale(GameState gameState) throws AzioneNonEseguibile{
		//throw new AzioneNonEseguibile("Il tipo di azione non può essere eseguita!"); 
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile"));
	}
	
	public default void transizioneVeloce(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile"));
	};

	public default void transizioneSecondaPrincipale(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile"));
		};
	
	public default void transizionePescaCarta(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile"));
	};
	
	public default void transizioneOfferta(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile"));
	}


	public default void transizionePassa(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile"));
	};
	
	public List<String> getAzioni();
	
	
}
