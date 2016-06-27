package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import server.model.azioni.Passa;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class PassaDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testAccept() {
		PassaDTO passa=new PassaDTO();
		
		Passa azione=(Passa) passa.accept(visitor);
		
		assertTrue(azione instanceof Passa);
	}

	@Test
	public void testToString() {
		PassaDTO passa=new PassaDTO();
		assertEquals("Passa  [Passa]", passa.toString());
	}
}
