package game.macchinaStati;

import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.azioni.AcquistoTesseraPermesso;
import game.azioni.Azione;
import game.azioni.CostruzioneAiutoRe;
import game.azioni.CostruzioneTesseraPermesso;
import game.azioni.ElezioneConsigliere;
import game.notify.AzioniNotify;
import game.notify.ErrorNotify;
import utility.exception.AzioneNonEseguibile;

public class Stato10 implements Stato {
	
	private List<Azione> azioni;

	public Stato10(GameState gameState) {
		System.out.println("[SERVER] "+this);
		azioni=Arrays.asList(new ElezioneConsigliere(), new AcquistoTesseraPermesso(),
				new CostruzioneTesseraPermesso(), new CostruzioneAiutoRe());
		gameState.notifyObserver(new ErrorNotify("AZIONI PRINCIPALI", Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	@Override
	public void transizionePrincipale(GameState gameState) throws AzioneNonEseguibile {
		if(!gameState.isBonusAzionePrincipale()){
			if(gameState.isUltimoGiro()){
				if(gameState.lastNextPlayer())
					gameState.setStato(new StatoFinePartita(gameState));
				else{
					gameState.nextPlayer();
					gameState.setStato(new StartEnd(gameState));
					}
			}
			else {
				gameState.nextPlayer();
				gameState.prossimoTurno();
		
				if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
					gameState.setStato(new StartEnd(gameState));
				else
					gameState.setStato(new StatoOffertaMarket(gameState));
				}
			}	
		else {
			gameState.setBonusAzionePrincipale(false);	
			gameState.setStato(new Stato10(gameState));
		}
	}

	@Override
	public void transizioneBonus(GameState gameState){
		gameState.setStato(new StatoBonus(gameState,this));
	}
	
	@Override
	public List<Azione> getAzioni() {
		return this.azioni;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stato10";
	}

}
