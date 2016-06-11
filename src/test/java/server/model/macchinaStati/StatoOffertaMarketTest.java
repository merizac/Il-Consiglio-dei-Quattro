package server.model.macchinaStati;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import server.model.azioni.Passa;
import server.model.azioni.azioniMarket.AzioneOfferta;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.market.Marketable;
import server.model.market.Offerta;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class StatoOffertaMarketTest {

	static GameState gameState;
	static List<Notify> notify;
	static Giocatore a;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		notify = new ArrayList<>();
		a=new Giocatore("A");
		Giocatore b=new Giocatore("B");
		Giocatore c=new Giocatore("C");		
		giocatori.add(a);
		giocatori.add(b);
		giocatori.add(c);
		gameState=new GameState();
		Observer<Notify> observer = new Observer<Notify>() {

			@Override
			public void update(Notify c) {
				notify.add(c);
			}
		};
		gameState.registerObserver(observer);
		gameState.start(giocatori);
	}

	@Test
	public void testStatoOffertaMarket() {
		notify.clear();
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		assertTrue(gameState.getStato() instanceof StatoOffertaMarket);
		assertTrue(notify.get(0) instanceof AzioniNotify);
	}

	@Test
	public void testTransizionePassaNumeroTurniDiversoDaZero() {
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		gameState.setNumeroTurni(3);
		
		statoOffertaMarket.transizionePassa(gameState);
		
		assertTrue(gameState.getStato() instanceof StatoOffertaMarket);
	}
	
	@Test
	public void testTransizionePassaNumeroTurniUgualeAZeroAndOfferteMarketVuote() {
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		gameState.setNumeroTurni(1);
		gameState.getOfferteMarket().clear();
		
		statoOffertaMarket.transizionePassa(gameState);
		
		assertTrue(gameState.getStato() instanceof StartEnd);
	}
	
	@Test
	public void testTransizionePassaNumeroTurniUgualeAZeroAndOfferteMarketNonVuote() {
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		gameState.setNumeroTurni(1);
		gameState.getOfferteMarket().add(new Offerta(new Giocatore("G"), new Aiutante(1), 1));
		
		statoOffertaMarket.transizionePassa(gameState);
		
		assertTrue(gameState.getStato() instanceof StatoAcquistoMarket);
	}

	@Test
	public void testTransizioneOfferta() {
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);

		statoOffertaMarket.transizioneOfferta(gameState);
		
		assertTrue(gameState.getStato() instanceof StatoOffertaMarket);

	}

	@Test
	public void testGetAzioni(){
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		assertTrue(statoOffertaMarket.getAzioni().size()==2);
		assertTrue(statoOffertaMarket.getAzioni().get(0) instanceof AzioneOfferta);
		assertTrue(statoOffertaMarket.getAzioni().get(1) instanceof Passa);
	}
	
	@Test
	public void testTransizionePrincipale(){
		notify.clear();
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		
		statoOffertaMarket.transizionePrincipale(gameState);

		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizioneVeloce(){
		notify.clear();
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		
		statoOffertaMarket.transizioneVeloce(gameState);

		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizioneSecondaPrincipale(){
		notify.clear();
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		
		statoOffertaMarket.transizioneSecondaPrincipale(gameState);

		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizionePescaCarta(){
		notify.clear();
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		
		statoOffertaMarket.transizionePescaCarta(gameState);

		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizioneBonus(){
		notify.clear();
		StatoOffertaMarket statoOffertaMarket=new StatoOffertaMarket(gameState);
		gameState.setStato(statoOffertaMarket);
		
		statoOffertaMarket.transizioneBonus(gameState);

		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
}
