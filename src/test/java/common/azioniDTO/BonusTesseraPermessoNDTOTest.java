package common.azioniDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.BonusTesseraPermessoNDTO;
import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.azioniBonus.BonusGettoneN;
import server.model.azioni.azioniBonus.BonusTesseraPermessoN;
import server.model.game.CittàBonus;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import utility.ParameterException;

public class BonusTesseraPermessoNDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	static Regione regione;
	static RegioneDTO regioneDTO;
	static TesseraPermesso tessera;
	static TesseraPermessoDTO tesseraDTO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		regione=gameState.getRegioni().get(0);
		regioneDTO=new RegioneDTO();
		regioneDTO.inizializza(regione);
		
		tessera=gameState.getRegioni().get(0).getTesserePermessoScoperte().get(0);
		tesseraDTO=new TesseraPermessoDTO();
		tesseraDTO.inizializza(tessera);
		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetRegione() {
		BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO=new BonusTesseraPermessoNDTO();
		bonusTesseraPermessoNDTO.setRegione(regioneDTO);
		assertTrue(regioneDTO==bonusTesseraPermessoNDTO.getRegione());
	}

	@Test
	public void testSetRegione() {
		BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO=new BonusTesseraPermessoNDTO();
		bonusTesseraPermessoNDTO.setRegione(regioneDTO);
		assertTrue(regioneDTO==bonusTesseraPermessoNDTO.getRegione());
	}

	@Test
	public void testGetTesseraScoperta() {
		BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO=new BonusTesseraPermessoNDTO();
		bonusTesseraPermessoNDTO.setTesseraScoperta(tesseraDTO);
		
		assertTrue(tesseraDTO==bonusTesseraPermessoNDTO.getTesseraScoperta());
	}

	@Test
	public void testSetTesseraScoperta() {
		BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO=new BonusTesseraPermessoNDTO();
		bonusTesseraPermessoNDTO.setTesseraScoperta(tesseraDTO);
		
		assertTrue(tesseraDTO==bonusTesseraPermessoNDTO.getTesseraScoperta());
	}

	@Test
	public void testAccept() throws ParameterException {
		BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO=new BonusTesseraPermessoNDTO();

		bonusTesseraPermessoNDTO.setRegione(regioneDTO);
		bonusTesseraPermessoNDTO.setTesseraScoperta(tesseraDTO);
		
		BonusTesseraPermessoN azioneParser=(BonusTesseraPermessoN) bonusTesseraPermessoNDTO.accept(visitor);
		
		assertTrue(tessera==azioneParser.getTesseraScoperta());
		assertTrue(regione==azioneParser.getRegione());
	}

}
