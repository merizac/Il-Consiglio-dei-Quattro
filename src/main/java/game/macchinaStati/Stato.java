package game.macchinaStati;

import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.notify.ErrorNotify;
import utility.exception.AzioneNonEseguibile;

public interface Stato{

	public default void transizionePrincipale(GameState gameState) throws AzioneNonEseguibile{
		//throw new AzioneNonEseguibile("Il tipo di azione non può essere eseguita!"); 
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	}
	
	public default void transizioneVeloce(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};

	public default void transizioneSecondaPrincipale(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
		};
	
	public default void transizionePescaCarta(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};
	
	public default void transizioneOfferta(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};


	public default void transizionePassa(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};
	
	public default void transizioneBonus(GameState gameState) throws AzioneNonEseguibile{
		gameState.notifyObserver(new ErrorNotify("azione non eseguibile", Arrays.asList(gameState.getGiocatoreCorrente())));
	};
	
	public default void transizioneExit(GameState gameState, Giocatore giocatore){
		if(gameState.isUltimoGiro()){
			if(!gameState.lastNextPlayer()){

				gameState.setStato(new StartEnd(gameState));}
			else{
				gameState.setStato(new StatoFinePartita(gameState));}
			
		}
		else{
		gameState.getGiocatoriDisconnessi().add(giocatore);
		gameState.getGiocatori().remove(giocatore);
		gameState.nextPlayer();
		gameState.prossimoTurno();
			if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
			gameState.setStato(new StartEnd(gameState));
			else
			gameState.setStato(new StatoOffertaMarket(gameState));
		}	
	}
	
	public List<Azione> getAzioni();



	
	
}
