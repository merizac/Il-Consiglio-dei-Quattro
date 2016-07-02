package server.model.azioni.azioniMarket;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.AzioneAcquistoDTO;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.macchinaStati.StartEnd;
import server.model.market.Offerta;
import server.model.notify.AvversarioNotify;
import server.model.notify.AzioniNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class AzioneAcquistoTest {

	static GameState gameState;
	static Giocatore giocatore;
	static Offerta offerta;
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

		offerta=new Offerta(giocatore, new Aiutante(1), 2);
	}

	@Test
	public void testEseguiAzioneNonBuonFineCostaTroppo() {
		notify.clear();
		AzioneAcquisto azione=new AzioneAcquisto();
		azione.setOfferta(new Offerta(new Giocatore("B"), new Aiutante(1), 20));
		azione.setAcquirente(giocatore);
		
		azione.eseguiAzione(gameState);
		
		assertTrue(notify.get(0) instanceof MessageNotify);
		assertEquals(1, notify.size());
	}
	
	@Test
	public void testEseguiAzioneNonBuonFineNonCiSonoOfferte() {
		notify.clear();
		/*AzioneAcquisto azione=new AzioneAcquisto();
		azione.setOfferta(new Offerta(new Giocatore("B"), new Aiutante(1), 20));
		azione.setAcquirente(giocatore);*/
		Giocatore venditore=new Giocatore("B");
		Offerta offertaMarket=new Offerta(venditore, new Aiutante(1), 1);
		gameState.getOfferteMarket().clear();
		AzioneAcquisto azione=new AzioneAcquisto();
		venditore.setAiutanti(new Aiutante(3));
		venditore.setPunteggioRicchezza(2);
		azione.setOfferta(offertaMarket);
		azione.setAcquirente(giocatore);
		giocatore.setPunteggioRicchezza(2);
		giocatore.setAiutanti(new Aiutante(4));
		azione.eseguiAzione(gameState);
		
		System.out.println(notify);
		assertTrue(notify.get(0) instanceof AvversarioNotify);
		assertTrue(notify.get(1) instanceof GiocatoreNotify);
		assertTrue(notify.get(2) instanceof MessageNotify);
		assertTrue(notify.get(3) instanceof AzioniNotify);
		assertEquals(4, notify.size());
		assertTrue(gameState.getStato() instanceof StartEnd);
	}

	@Test
	public void testEseguiAzioneBuonFine() {
		notify.clear();
		Giocatore venditore=new Giocatore("B");
		Offerta offertaMarket=new Offerta(venditore, new Aiutante(1), 1);
		gameState.getOfferteMarket().clear();
		AzioneAcquisto azione=new AzioneAcquisto();
		venditore.setAiutanti(new Aiutante(3));
		venditore.setPunteggioRicchezza(2);
		azione.setOfferta(offertaMarket);
		azione.setAcquirente(giocatore);
		giocatore.setPunteggioRicchezza(2);
		giocatore.setAiutanti(new Aiutante(4));
		gameState.getOfferteMarket().add(offertaMarket);
		azione.eseguiAzione(gameState);
		
		assertEquals(2+1, venditore.getPunteggioRicchezza());
		assertEquals(2, venditore.getAiutanti().getAiutante());
		assertEquals(2-1, giocatore.getPunteggioRicchezza());
		assertEquals(5, giocatore.getAiutanti().getAiutante());
		assertEquals(0, gameState.getOfferteMarket().size());
	}

	@Test
	public void testIsTurno() {
		assertTrue(giocatore==gameState.getGiocatori().get(0));
	}

	@Test
	public void testGetAzioneDTO() {
		assertTrue(new AzioneAcquisto().getAzioneDTO() instanceof AzioneAcquistoDTO);
	}

	@Test
	public void testGetOfferta() {
		AzioneAcquisto azione=new AzioneAcquisto();
		azione.setOfferta(offerta);
		
		assertTrue(offerta==azione.getOfferta());
	}

	@Test
	public void testSetOfferta() {
		AzioneAcquisto azione=new AzioneAcquisto();
		azione.setOfferta(offerta);
		
		assertTrue(offerta==azione.getOfferta());
	}

	@Test
	public void testGetAcquirente() {
		AzioneAcquisto azione=new AzioneAcquisto();
		azione.setAcquirente(giocatore);
		
		assertTrue(giocatore==azione.getAcquirente());
	}

	@Test
	public void testSetAcquirente() {
		AzioneAcquisto azione=new AzioneAcquisto();
		azione.setAcquirente(giocatore);
		
		assertTrue(giocatore==azione.getAcquirente());
	}

	@Test
	public void testToString() {
		assertEquals("Acquista  [Acquista]", new AzioneAcquisto().toString());
	}

}
