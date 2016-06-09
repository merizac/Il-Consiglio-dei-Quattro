package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameState;
import game.Giocatore;
import game.TesseraPermesso;
import game.azioni.BonusTesseraAcquistataN;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class BonusTesseraAcquistataNDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	static TesseraPermessoDTO tesseraPermesso;
	static TesseraPermesso tessera;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		tessera=gameState.getRegioni().get(0).getTesserePermessoScoperte().get(0);
		tesseraPermesso=new TesseraPermessoDTO();
		tesseraPermesso.inizializza(tessera);
		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetTesseraPermesso() {
		BonusTesseraAcquistataNDTO azione=new BonusTesseraAcquistataNDTO();
		azione.setTesseraPermesso(tesseraPermesso);
		
		assertTrue(azione.getTesseraPermesso()==tesseraPermesso);
	}

	@Test
	public void testSetTesseraPermesso() {
		BonusTesseraAcquistataNDTO azione=new BonusTesseraAcquistataNDTO();
		azione.setTesseraPermesso(tesseraPermesso);
		
		assertTrue(azione.getTesseraPermesso()==tesseraPermesso);
	}

	@Test
	public void testAccept() {
		BonusTesseraAcquistataNDTO azione=new BonusTesseraAcquistataNDTO();
		
		azione.setTesseraPermesso(tesseraPermesso);
		azione.setUsata(false);
		gameState.getGiocatoreCorrente().aggiungiTesseraPermesso(tessera);
		
		BonusTesseraAcquistataN azioneParser=(BonusTesseraAcquistataN) azione.accept(visitor);
		
		assertEquals(tessera, azioneParser.getTesseraPermesso());
	}

	@Test
	public void testIsUsata() {
		BonusTesseraAcquistataNDTO azione=new BonusTesseraAcquistataNDTO();
		azione.setUsata(true);
		
		assertTrue(azione.isUsata());
	}

	@Test
	public void testSetUsata() {
		BonusTesseraAcquistataNDTO azione=new BonusTesseraAcquistataNDTO();
		azione.setUsata(false);
		
		assertTrue(!azione.isUsata());
	}

}
