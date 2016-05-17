package controller;

import java.util.List;

import game.GameState;
import game.azioni.Azione;

public class Stato11 extends Stato {

	private List<String> azionePrincipale;
	private List<String> azioneVeloce;
	/**
	 * @param azionePrincipale
	 * @param azioneVeloce
	 */
	public Stato11() {
		this.azionePrincipale = riempiAzioniPrincipali();
		this.azioneVeloce = riempiAzioniVeloci();
	}
	
	@Override
	public void transizionePrincipale(GameState gameState){
		if(!gameState.isBonusAzionePrincipale()){
		gameState.setStato(new Stato01());
		}
		else
			gameState.setBonusAzionePrincipale(false);
	}
	
	@Override
	public void transizioneVeloce(GameState gameState){
		gameState.setStato(new Stato10());
	}
	
	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		gameState.setStato(new Stato10());
	}
	
	@Override
	public void transizionePescaCarta(GameState gameState) {
		throw new IllegalArgumentException("Il tipo di azione non può essere eseguita!"); 
	}

}
