package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
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
		azioni= new ArrayList<>();
		riempiAzioni();
		gameState.notifyObserver(new AzioniNotify(getAzioni(), 
					Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	private void riempiAzioni() {
		azioni.add("\nOfferta");
		azioni.add("Passa");
		
	}

	@Override
	public void transizionePassa(GameState gameState){
		gameState.decrementaTurno();
		gameState.nextPlayer();
		if(gameState.getNumeroTurni()!=0){
			System.out.println("market giocatorecorrente: "+ gameState.getGiocatoreCorrente().getNome());
			gameState.setStato(new StatoOffertaMarket(gameState));

		}
		else
			gameState.setStato(new StatoAcquistoMarket(gameState));
	}
	
	@Override
	public void transizioneOfferta(GameState gameState){
		gameState.setStato(new StatoOffertaMarket(gameState));
	}

	@Override
	public List<String> getAzioni() {
		return azioni;
	}

}
