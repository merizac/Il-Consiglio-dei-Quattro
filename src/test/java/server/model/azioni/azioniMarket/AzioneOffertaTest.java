package server.model.azioni.azioniMarket;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.AzioneOffertaDTO;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class AzioneOffertaTest {

	static GameState gameState;
	static Giocatore giocatore;
	static List<Notify> notify;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		notify = new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		giocatori.add(new Giocatore("B"));
		giocatori.add(new Giocatore("C"));
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
	public void venditoreNonPossiedeOggetto() {
		notify.clear();
		AzioneOfferta azione=new AzioneOfferta();
		giocatore.setAiutanti(new Aiutante(0));
		azione.setMarketable(new Aiutante(1));
		azione.setPrezzo(2);
		azione.eseguiAzione(gameState);

		assertTrue(notify.get(0) instanceof MessageNotify);
		assertEquals(1, notify.size());
	}
	
	@Test
	public void venditorePossiedeOggetto() {
		notify.clear();
		gameState.getOfferteMarket().clear();
		AzioneOfferta azione=new AzioneOfferta();
		giocatore.setAiutanti(new Aiutante(2));
		azione.setMarketable(new Aiutante(1));
		azione.setPrezzo(2);
		azione.eseguiAzione(gameState);
		
		assertEquals(2, giocatore.getAiutanti().getAiutante());
		assertEquals(1, gameState.getOfferteMarket().size());
		assertEquals(2, gameState.getOfferteMarket().get(0).getPrezzo());
		assertEquals(1, ((Aiutante)gameState.getOfferteMarket().get(0).getMarketable()).getAiutante());
	}

	@Test
	public void testGetAzioneDTO() {
		assertTrue(new AzioneOfferta().getAzioneDTO() instanceof AzioneOffertaDTO);
	}

	@Test(expected=NullPointerException.class)
	public void testSetMarketable() {
		AzioneOfferta azione=new AzioneOfferta();
		azione.setMarketable(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetPrezzo() {
		AzioneOfferta azione=new AzioneOfferta();
		azione.setPrezzo(-2);
	}

	@Test
	public void testToString() {
		assertEquals("Fai un'offerta  [Offerta]", new AzioneOfferta().toString());
	}

}
