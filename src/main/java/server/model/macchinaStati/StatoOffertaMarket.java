package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.Passa;
import server.model.azioni.azioniMarket.AzioneOfferta;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;

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
		return this.azioni; 
	}

}
