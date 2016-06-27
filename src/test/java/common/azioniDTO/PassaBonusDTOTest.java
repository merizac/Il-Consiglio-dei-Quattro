package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.azioniBonus.BonusTesseraAcquistataN;
import server.model.azioni.azioniBonus.BonusTesseraPermessoN;
import server.model.azioni.azioniBonus.PassaBonus;
import server.model.bonus.BonusTesseraAcquistata;
import server.model.game.GameState;
import server.model.game.Giocatore;
import utility.ParameterException;

public class PassaBonusDTOTest {

	static GameState gameState;
	static GiocatoreDTO giocatoreDTO;
	static AzioneVisitor visitor;
	static Giocatore giocatore;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
		giocatoreDTO=new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testAccept() throws ParameterException {
		PassaBonusDTO passa=new PassaBonusDTO();
		
		RegioneDTO regioneDTO=new RegioneDTO();
		regioneDTO.inizializza(gameState.getRegioni().get(0));
		TesseraPermessoDTO tesseraScopertaDTO=new TesseraPermessoDTO();
		tesseraScopertaDTO.inizializza(gameState.getRegioni().get(0).getTesserePermessoScoperte().get(0));

		BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO=new BonusTesseraPermessoNDTO();
		bonusTesseraPermessoNDTO.setRegione(regioneDTO);
		bonusTesseraPermessoNDTO.setTesseraScoperta(tesseraScopertaDTO);
		
		passa.setBonus(bonusTesseraPermessoNDTO);
		
		PassaBonus parser=passa.accept(visitor);
		
//		assertEquals(bonusTesseraPermessoNDTO, parser.getBonus());
	}

	@Test
	public void testGetBonus() {
		PassaBonusDTO passa=new PassaBonusDTO();
		IngaggioAiutanteDTO bonus=new IngaggioAiutanteDTO();
		passa.setBonus(bonus);
		
		assertTrue(passa.getBonus() instanceof IngaggioAiutanteDTO);
	}

	@Test
	public void testSetBonus() {
		PassaBonusDTO passa=new PassaBonusDTO();
		IngaggioAiutanteDTO bonus=new IngaggioAiutanteDTO();
		passa.setBonus(bonus);
		
		assertTrue(passa.getBonus() instanceof IngaggioAiutanteDTO);
	}

	@Test
	public void testToString() {
		PassaBonusDTO passa=new PassaBonusDTO();
		assertEquals("Passa  [Passa Bonus]", passa.toString());
	}

}
