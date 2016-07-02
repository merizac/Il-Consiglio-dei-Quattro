package server.model.azioni;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.GameState;
import server.model.game.Giocatore;

public class AzioneTest {

	static GameState gameState;
	static Giocatore giocatore;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		giocatori.add(new Giocatore("B"));
		giocatori.add(new Giocatore("C"));
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsTurnoGiocatoreNull() {
		Azione azione=new Passa();
		azione.isTurno(null, gameState);
	}
	
	@Test(expected=NullPointerException.class)
	public void testIsTurnoGameStateNull() {
		Azione azione=new Passa();
		azione.isTurno(giocatore, null);
	}

	@Test
	public void returnGiocatoreTrue() {
		Azione azione=new Passa();
		assertTrue(azione.isTurno(giocatore, gameState));
	}
	
	@Test
	public void returnGiocatoreFalse() {
		Azione azione=new Passa();
		assertFalse(azione.isTurno(new Giocatore("S"), gameState));
	}
}
