package game.macchinaStati;

import java.util.List;
import game.GameState;
import game.notify.AzioniNotify;

public class StatoOffertaMarket implements Stato {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5599670651827007425L;
	private List<String> azioni;
	
	public StatoOffertaMarket(GameState gameState) {
		riempiAzioni();
		//gameState.notifyObserver(new AzioniNotify(getAzioni()));
	}

	private void riempiAzioni() {
		azioni.add("Offerta");
		azioni.add("Passa");
		
	}

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
	public List<String> getAzioni() {
		// TODO Auto-generated method stub
		return null;
	}

}
