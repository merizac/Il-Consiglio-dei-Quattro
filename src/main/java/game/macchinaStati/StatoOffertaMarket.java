package game.macchinaStati;

import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.azioni.Azione;
import game.azioni.AzioneAcquisto;
import game.azioni.Passa;
import game.notify.AzioniNotify;

public class StatoOffertaMarket implements Stato {

	public StatoOffertaMarket(GameState gameState) {
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), 
					Arrays.asList(gameState.getGiocatoreCorrente())));
	}


	@Override
	public void transizionePassa(GameState gameState){
		gameState.decrementaTurno();
		gameState.nextPlayer();
		if(gameState.getNumeroTurni()!=0){
			System.out.println("market giocatorecorrente: "+ gameState.getGiocatoreCorrente().getNome());
			gameState.setStato(new StatoOffertaMarket(gameState));

		}
		else{
			if(gameState.getOfferteMarket().isEmpty())
				gameState.setStato(new StartEnd(gameState));
			else
				gameState.setStato(new StatoAcquistoMarket(gameState));}
	}
	
	@Override
	public void transizioneOfferta(GameState gameState){
		gameState.setStato(new StatoOffertaMarket(gameState));
	}

	@Override
	public List<Azione> getAzioni() {
		return Arrays.asList(new AzioneAcquisto(),new Passa());
	}

}
