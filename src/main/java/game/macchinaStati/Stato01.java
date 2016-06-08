package game.macchinaStati;

import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.azioni.Azione;
import game.azioni.CambioTesseraPermesso;
import game.azioni.ElezioneConsigliereVeloce;
import game.azioni.IngaggioAiutante;
import game.azioni.Passa;
import game.azioni.SecondaAzionePrincipale;
import game.notify.AzioniNotify;
import game.notify.ErrorNotify;
import utility.exception.AzioneNonEseguibile;

public class Stato01 implements Stato {


	public Stato01(GameState gameState) {
		System.out.println(this);
		gameState.notifyObserver(new ErrorNotify("AZIONI VELOCI", Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
		
	}


	@Override
	public void transizioneVeloce(GameState gameState) throws AzioneNonEseguibile {
		if(gameState.isUltimoGiro()){
			if(!gameState.lastNextPlayer()){

				gameState.setStato(new StartEnd(gameState));}
			else{
				gameState.setStato(new StatoFinePartita(gameState));}
			
		}
		else{
		gameState.nextPlayer();
		gameState.prossimoTurno();
			if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
			gameState.setStato(new StartEnd(gameState));
			else
			gameState.setStato(new StatoOffertaMarket(gameState));
		}	
	}

	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		gameState.setStato(new Stato10(gameState));
		
	}
	
	@Override
	public void transizionePassa(GameState gameState) throws AzioneNonEseguibile{
		this.transizioneVeloce(gameState);
	}
	

	@Override
	public List<Azione> getAzioni() {
		return Arrays.asList(new IngaggioAiutante(), new CambioTesseraPermesso(), 
				new ElezioneConsigliereVeloce(), new SecondaAzionePrincipale(), new Passa());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stato01";
	}


	
	

	
}
