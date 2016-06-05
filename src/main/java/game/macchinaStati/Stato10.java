package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.notify.AzioniNotify;
import utility.exception.AzioneNonEseguibile;

public class Stato10 implements Stato {

	private List<String> azioni;

	/**
	 * @param azioni
	 */
	public Stato10(GameState gameState) {
		System.out.println(this);
		riempiAzioni();
		gameState.notifyObserver(new AzioniNotify(getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	private void riempiAzioni() {
		this.azioni = new ArrayList<String>();
		azioni.add("AZIONI PRINCIPALI:");
		azioni.add("Eleggere un consigliere [P1]");
		azioni.add("Acquistare una tessera permesso di costruzione [P2]");
		azioni.add("Costruire un emporio usando una tessera permesso [P3]");
		azioni.add("costruire un emporio con l'aiuto del re [P4]");
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
		else
			gameState.setBonusAzionePrincipale(false);	
			gameState.setStato(new Stato10(gameState));
	}

	@Override
	public void transizioneBonus(GameState gameState){
		gameState.setStato(new StatoBonus(gameState,this));
	}
	
	@Override
	public List<String> getAzioni() {
		return azioni;
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
