package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.GiocatoreDTO;
import server.model.azioni.Exit;
import server.model.game.GameState;
import server.model.game.Giocatore;
import utility.ParameterException;

public class ExitDTOTest {

	static GameState gameState;
	static GiocatoreDTO giocatoreDTO;
	static AzioneVisitor visitor;
	static Giocatore giocatore;
	static ExitDTO exitDTO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");		giocatoreDTO=new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Before
	public void testExitDTO() {
		exitDTO=new ExitDTO(giocatoreDTO);
	}

	@Test
	public void testAccept() throws ParameterException {
		Exit exit=exitDTO.accept(visitor);
		assertEquals(giocatore, exit.getGiocatore());
	}

	@Test
	public void testGetGiocatoreDTO() {
		assertTrue(giocatoreDTO==exitDTO.getGiocatoreDTO());
	}

}
