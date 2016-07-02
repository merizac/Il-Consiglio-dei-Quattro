package server.model.macchinaStati;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.PescaCarta;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class StartEndTest {

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
		gameState.start(giocatori, "mappa1");
	}

	@Test
	public void testStartEnd() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		gameState.setStato(startEnd);
		assertTrue(gameState.getStato() instanceof StartEnd);
		assertTrue(notify.get(1) instanceof AzioniNotify);
	}

	@Test
	public void testTransizionePescaCarta() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		startEnd.transizionePescaCarta(gameState);
		assertTrue(gameState.getStato() instanceof Stato11);
	}

	@Test
	public void testGetAzioni() {
		StartEnd startEnd=new StartEnd(gameState);
		gameState.setStato(startEnd);
		assertTrue(startEnd.getAzioni().size()==1);
		assertTrue(startEnd.getAzioni().get(0) instanceof PescaCarta);
	}

	@Test
	public void azioniNonEseguibiliTransizioneBonus() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		startEnd.transizioneBonus(gameState);
 
		assertTrue(notify.get(2) instanceof MessageNotify);
	}
	
	@Test
	public void azioniNonEseguibiliTransizioneOfferta() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		startEnd.transizioneOfferta(gameState);
 
		assertTrue(notify.get(2) instanceof MessageNotify);
	}
	
	@Test
	public void azioniNonEseguibiliTransizioePassa() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		startEnd.transizionePassa(gameState);
 
		assertTrue(notify.get(2) instanceof MessageNotify);
	}

	@Test
	public void azioniNonEseguibiliTransizionePrincipale() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		startEnd.transizionePrincipale(gameState);
 
		assertTrue(notify.get(2) instanceof MessageNotify);
	}
	
	@Test
	public void azioniNonEseguibiliTransizioneSecondaPrincipale() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		startEnd.transizioneSecondaPrincipale(gameState);
		assertTrue(notify.get(2) instanceof MessageNotify);
	}
	
	@Test
	public void azioniNonEseguibiliTransizioneVeloce() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		startEnd.transizioneVeloce(gameState);
 
		assertTrue(notify.get(2) instanceof MessageNotify);
	}

}
