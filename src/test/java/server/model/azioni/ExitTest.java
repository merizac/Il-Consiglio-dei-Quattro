package server.model.azioni;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.ExitDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.macchinaStati.StartEnd;
import server.model.notify.GiocatoreDisconnessoNotify;
import server.model.notify.Notify;
import utility.Observer;

public class ExitTest {

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
		gameState.setGiocatoreCorrente(giocatore);
	}

	@Test
	public void testEseguiAzione() {
		Exit exit=new Exit();
		notify.clear();
		exit.setGiocatore(giocatore);
		exit.eseguiAzione(gameState);
		
		assertTrue(gameState.getGiocatoriFinePartita().get(0)==giocatore);
		assertTrue(gameState.getStato() instanceof StartEnd);
		assertEquals(2, gameState.getGiocatori().size());
		assertTrue(notify.get(0) instanceof GiocatoreDisconnessoNotify);
	}

	@Test
	public void testGetAzioneDTO() {
		Exit exit=new Exit();
		assertTrue(exit.getAzioneDTO() instanceof ExitDTO);
	}

	@Test
	public void testSetGiocatore() {
		Giocatore player=new Giocatore("A");
		Exit exit=new Exit();
		exit.setGiocatore(player);
		
		assertTrue(player==exit.getGiocatore());
	}

	@Test
	public void testGetGiocatore() {
		Giocatore player=new Giocatore("A");
		Exit exit=new Exit();
		exit.setGiocatore(player);
		
		assertTrue(player==exit.getGiocatore());
	}
}
