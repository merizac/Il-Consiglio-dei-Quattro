package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.CittàDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.azioniPrincipali.CostruzioneTesseraPermesso;
import server.model.game.Città;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.TesseraPermesso;
import utility.ParameterException;

public class CostruzioneTesseraPermessoDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	static TesseraPermesso tessera;
	static TesseraPermessoDTO tesseraDTO;
	static Città città;
	static CittàDTO cittàDTO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");		
		tessera=gameState.getRegioni().get(0).getTesserePermessoScoperte().get(0);
		gameState.getGiocatoreCorrente().aggiungiTesseraPermesso(tessera);
		tesseraDTO=new TesseraPermessoDTO();
		tesseraDTO.inizializza(tessera);
		città=tessera.getCittà().get(0);
		cittàDTO=new CittàDTO();
		cittàDTO.inizializza(città);
		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetTesseraPermesso() {
		CostruzioneTesseraPermessoDTO costruzione=new CostruzioneTesseraPermessoDTO();
		costruzione.setTesseraPermesso(tesseraDTO);
		
		assertTrue(tesseraDTO==costruzione.getTesseraPermesso());
	}

	@Test
	public void testSetTesseraPermesso() {
		CostruzioneTesseraPermessoDTO costruzione=new CostruzioneTesseraPermessoDTO();
		costruzione.setTesseraPermesso(tesseraDTO);
		
		assertTrue(tesseraDTO==costruzione.getTesseraPermesso());
	}

	@Test
	public void testGetCittà() {
		CostruzioneTesseraPermessoDTO costruzione=new CostruzioneTesseraPermessoDTO();
		costruzione.setCittà(cittàDTO);
		
		assertTrue(cittàDTO==costruzione.getCittà());
	}

	@Test
	public void testSetCittà() {
		CostruzioneTesseraPermessoDTO costruzione=new CostruzioneTesseraPermessoDTO();
		costruzione.setCittà(cittàDTO);
		
		assertTrue(cittàDTO==costruzione.getCittà());	
	}

	@Test
	public void testAccept() throws ParameterException {
		CostruzioneTesseraPermessoDTO costruzione=new CostruzioneTesseraPermessoDTO();
		costruzione.setCittà(cittàDTO);
		costruzione.setTesseraPermesso(tesseraDTO);

		
		CostruzioneTesseraPermesso azioneParser=(CostruzioneTesseraPermesso) costruzione.accept(visitor);
		
		assertTrue(città==azioneParser.getCittàCostruzione());
		assertTrue(tessera==azioneParser.getTesseraPermessoScoperta());
	}
	
	@Test
	public void testToString() {
		CostruzioneTesseraPermessoDTO costruzione=new CostruzioneTesseraPermessoDTO();
		assertEquals("Costruire con una tessera permesso  [P3]", costruzione.toString());
	}

}
