package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.Passa;
import server.model.azioni.azioniMarket.AzioneOfferta;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;
import server.model.notify.GiocatoreMarketNotify;
import server.model.notify.OffertaNotify;

public class StatoOffertaMarket implements Stato {

	private List<Azione> azioni;

	public StatoOffertaMarket(GameState gameState) {
		this.azioni=Arrays.asList(new AzioneOfferta(),new Passa());
		System.out.println("[SERVER] Azioni Stato Offerta: "+azioni);
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), 
					Arrays.asList(gameState.getGiocatoreCorrente())));
	}


	@Override
	public void transizionePassa(GameState gameState){
		System.out.println("NumeroTurno1: "+gameState.getNumeroTurni());
		
		gameState.decrementaTurno();
		gameState.nextPlayer();
		System.out.println("NumeroTurno2: "+gameState.getNumeroTurni());
		
		if(gameState.getNumeroTurni()!=0){
			System.out.println("market giocatorecorrente: "+ gameState.getGiocatoreCorrente().getNome());
			gameState.notifyObserver(new GiocatoreMarketNotify(gameState.getGiocatoreCorrente(), Arrays.asList(gameState.getGiocatoreCorrente())));
			gameState.notifyObserver(new OffertaNotify(gameState.getOfferteMarket(), gameState.getGiocatori()));
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
		return this.azioni; 
	}

}
