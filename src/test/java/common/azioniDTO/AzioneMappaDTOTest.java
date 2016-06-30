package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import server.model.game.GameState;
import server.model.game.Giocatore;
import utility.ParameterException;

public class AzioneMappaDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	static Giocatore giocatore;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testAzioneMappaDTO() {
		String mappa="A";
		AzioneMappaDTO azione=new AzioneMappaDTO(mappa);
		assertTrue(mappa==azione.getMappa());
	}

	@Test
	public void testGetMappa() {
		String mappa="A";
		AzioneMappaDTO azione=new AzioneMappaDTO(mappa);
		assertTrue(mappa==azione.getMappa());
	}

	@Test
	public void testAccept() throws ParameterException {
		AzioneMappaDTO azione=new AzioneMappaDTO("a");
		assertTrue(azione.accept(visitor)==null);
	}

}
