package server.model.macchinaStati;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.Passa;
import server.model.azioni.azioniMarket.AzioneAcquisto;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.market.Offerta;
import server.model.notify.AvversarioNotify;
import server.model.notify.AzioniNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.MarketNotify;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import server.model.notify.OffertaNotify;
import utility.Observer;

public class StatoAcquistoMarketTest {

	static GameState gameState;
	static List<Notify> notify;
	static Giocatore giocatore;
	
	@BeforeClass
	public static void init() throws IOException{
		notify = new ArrayList<>();
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		Observer<Notify> observer = new Observer<Notify>() {

			@Override
			public void update(Notify c) {
				notify.add(c);
			}
		};
		gameState.registerObserver(observer);
		gameState.start(giocatori, "mappa1");
	}

	@Test
	public void testStatoAcquistoMarket() {
		notify.clear();
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		assertTrue(gameState.getStato() instanceof StatoAcquistoMarket);
		assertTrue(statoAcquistoMarket.getGiocatori().contains(giocatore));
		assertEquals(1, statoAcquistoMarket.getGiocatori().size());
		assertEquals(3, notify.size());
		assertTrue(notify.get(0) instanceof AzioniNotify);
		assertTrue(notify.get(1) instanceof OffertaNotify);
		assertTrue(notify.get(2) instanceof MessageNotify);
	}

	@Test
	public void testTransizionePassaConGiocatori() {
		gameState.getGiocatori().add(new Giocatore("A"));
		gameState.getGiocatori().add(new Giocatore("B"));

		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		notify.clear();

		statoAcquistoMarket.transizionePassa(gameState);
		
		assertTrue(notify.get(0) instanceof OffertaNotify);
		assertTrue(notify.get(1) instanceof AzioniNotify);
		assertTrue(gameState.getStato() instanceof StatoAcquistoMarket);
	}
	
	@Test
	public void testTransizionePassaSenzaGiocatori() {
		while(gameState.getGiocatori().size()>1){
			gameState.getGiocatori().remove(gameState.getGiocatori().size()-1);
		}
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		
		notify.clear();

		statoAcquistoMarket.transizionePassa(gameState);
		
		assertEquals(3, notify.size());
		assertTrue(notify.get(0) instanceof MarketNotify);
		assertTrue(gameState.getStato() instanceof StartEnd);
	}

	@Test
	public void testTransizioneOffertaOfferteFinite() {
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		notify.clear();

		statoAcquistoMarket.transizioneOfferta(gameState);

		assertEquals(4, notify.size());
		assertTrue(gameState.getStato() instanceof StartEnd);
		assertTrue(notify.get(0) instanceof MessageNotify);
		assertTrue(notify.get(1) instanceof MarketNotify);
	}
	
	@Test
	public void testTransizioneOffertaTrue() {
		gameState.getOfferteMarket().add(new Offerta(new Giocatore("venditore"), new Aiutante(1), 2));
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		notify.clear();

		statoAcquistoMarket.transizioneOfferta(gameState);
		
		assertEquals(5, notify.size());
		assertTrue(gameState.getStato() instanceof StatoAcquistoMarket);
		assertTrue(notify.get(0) instanceof OffertaNotify);
		assertTrue(notify.get(1) instanceof AzioniNotify);
		assertTrue(notify.get(2) instanceof AvversarioNotify);
		assertTrue(notify.get(3) instanceof GiocatoreNotify);
		assertTrue(notify.get(4) instanceof MessageNotify);
	}

	@Test
	public void testGetAzioni() {
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		assertTrue(statoAcquistoMarket.getAzioni().size()==2);
		assertTrue(statoAcquistoMarket.getAzioni().get(0) instanceof AzioneAcquisto);
		assertTrue(statoAcquistoMarket.getAzioni().get(1) instanceof Passa);
	}

	@Test
	public void testGetGiocatori() {
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);

		assertEquals(gameState.getGiocatori(),statoAcquistoMarket.getGiocatori());
	}
	
	@Test
	public void testTransizionePrincipale() {
		notify.clear();
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		statoAcquistoMarket.transizionePrincipale(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizioneVeloce() {
		notify.clear();
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		statoAcquistoMarket.transizioneVeloce(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizionePescaCarta() {
		notify.clear();
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		statoAcquistoMarket.transizionePescaCarta(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);

	}
	
	@Test
	public void testTransizioneSecondaPrincipale() {
		notify.clear();
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		statoAcquistoMarket.transizioneSecondaPrincipale(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);

	}
	
	@Test
	public void testTransizioneBonus() {
		notify.clear();
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		statoAcquistoMarket.transizioneBonus(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testToString(){
		assertEquals("StatoAcquistoMarket", new StatoAcquistoMarket(gameState).toString());
	}
}
