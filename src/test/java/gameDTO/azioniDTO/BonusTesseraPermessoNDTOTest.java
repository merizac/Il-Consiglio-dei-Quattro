package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.CittàBonus;
import game.GameState;
import game.Giocatore;
import game.Regione;
import game.TesseraPermesso;
import game.azioni.BonusGettoneN;
import game.azioni.BonusTesseraPermessoN;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

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
	public void testAccept() {
		BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO=new BonusTesseraPermessoNDTO();

		bonusTesseraPermessoNDTO.setRegione(regioneDTO);
		bonusTesseraPermessoNDTO.setTesseraScoperta(tesseraDTO);
		
		BonusTesseraPermessoN azioneParser=(BonusTesseraPermessoN) bonusTesseraPermessoNDTO.accept(visitor);
		
		assertTrue(tessera==azioneParser.getTesseraScoperta());
		assertTrue(regione==azioneParser.getRegione());
	}

}
