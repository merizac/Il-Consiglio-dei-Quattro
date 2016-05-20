package controller;

import java.util.ArrayList;
import java.util.List;

import game.GameState;
import game.azioni.Azione;

public class StatoOffertaMarket implements Stato {
	
	@Override
	public void transizionePassa(GameState gameState){
		if(gameState.getNumeroTurni()!=0){
			gameState.decrementaTurno();
			gameState.nextPlayer();
			gameState.setStato(this);
		}
		else
			gameState.setStato(new StatoAcquistoMarket(gameState));
	}
	
	@Override
	public void transizioneOfferta(GameState gameState){
		gameState.setStato(this);
	}
	
	@Override
	public List<Azione> getAzioni() {
		List<Azione> azioniDisponibili=new ArrayList<>();
		return azioniDisponibili;
	}

}
