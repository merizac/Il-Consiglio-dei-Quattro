package server.model.macchinaStati;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.Passa;
import server.model.azioni.azioniMarket.AzioneAcquisto;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import server.model.notify.OffertaNotify;
import utility.Observer;

public class StatoAcquistoMarketTest {

	static GameState gameState;
	static List<Notify> notify;
	
	@BeforeClass
	public static void init() throws IOException{
		notify = new ArrayList<>();
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
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
	public void testStatoAcquistoMarket() {
		notify.clear();
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		assertTrue(gameState.getStato() instanceof StatoAcquistoMarket);
		assertTrue(notify.get(0) instanceof OffertaNotify);
		assertTrue(notify.get(1) instanceof AzioniNotify);
	}

	@Test
	public void testTransizionePassaConGiocatori() {
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		
		gameState.getGiocatori().add(new Giocatore("A"));
		gameState.getGiocatori().add(new Giocatore("B"));
		
		statoAcquistoMarket.transizionePassa(gameState);
			
		assertTrue(gameState.getStato() instanceof StartEnd);
	}
	
	@Test
	public void testTransizionePassaSenzaGiocatori() {
		notify.clear();
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		
		while(gameState.getGiocatori().size()>1){
			gameState.getGiocatori().remove(gameState.getGiocatori().size()-1);
		}

		
		statoAcquistoMarket.transizionePassa(gameState);
		
		assertTrue(gameState.getStato() instanceof StatoAcquistoMarket);
		assertTrue(notify.get(0) instanceof OffertaNotify);
		assertTrue(notify.get(1) instanceof AzioniNotify);
	}

	@Test
	public void testTransizioneOfferta() {
		notify.clear();
		StatoAcquistoMarket statoAcquistoMarket=new StatoAcquistoMarket(gameState);
		gameState.setStato(statoAcquistoMarket);
		
		statoAcquistoMarket.transizioneOfferta(gameState);
		
//		assertTrue(gameState.getStato() instanceof StatoAcquistoMarket);
		assertTrue(notify.get(0) instanceof OffertaNotify);
		assertTrue(notify.get(1) instanceof AzioniNotify);
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
}
