package server.model.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import client.grafica.gui.GUI;
import common.gameDTO.TesseraPermessoDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.bonus.BonusTesseraPermesso;
import server.model.game.Città;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.market.Offerta;

public class TesseraPermessoTest {

	static GameState gameState;
	static Regione regione;
	static TesseraPermesso tessera;
	static ArrayList<Città> città;
	static ArrayList<Bonus> bonus;
	
	@BeforeClass
	public static void setUp() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
	
		città=new ArrayList<>();
		bonus=new ArrayList<>();
		città.add(gameState.getRegioni().get(0).getCittàRegione().get(0));
		regione=gameState.getRegioni().get(0);
		bonus.add(new BonusAiutanti(2));
		
		tessera=new TesseraPermesso(città, bonus, regione);
		gameState.getGiocatoreCorrente().aggiungiTesseraPermesso(tessera);
	}
	
	@Test(expected=NullPointerException.class)
	public void testCittàNull() {
		new TesseraPermesso(null, bonus, regione);
	}

	@Test(expected=NullPointerException.class)
	public void testRegioneNull() {
		new TesseraPermesso(città, null, regione);
	}

	@Test(expected=NullPointerException.class)
	public void testBonusNull() {
		new TesseraPermesso(città, bonus, null);
	}

	@Test
	public void testGetRegione() {
		assertTrue(regione==tessera.getRegione());
	}

	@Test
	public void testGetCittà() {
		assertTrue(città==tessera.getCittà());
	}

	@Test
	public void testGetBonus() {
		assertTrue(bonus==tessera.getBonus());
	}

	@Test
	public void ilGiocatoreNONPossiedeLaTessera() {
		assertTrue(!gameState.getRegioni().get(1).getTesserePermessoScoperte().get(0).possiede(gameState.getGiocatoreCorrente()));
	}
	
	@Test
	public void ilGiocatorePossiedeLaTessera() {
		assertTrue(tessera.possiede(gameState.getGiocatoreCorrente()));
	}
	
	@Test
	public void testInstance() {
		assertTrue(tessera.instance() instanceof TesseraPermessoDTO);
	}
	
	@Test
	public void testToString(){
		assertEquals("TesseraPermesso [ (" + città + "), (" + bonus + ") ]", new TesseraPermesso(città, bonus, regione).toString());
	}
	
	@Test
	public void testAcquistaTrue() {
		TesseraPermesso tessera=new TesseraPermesso(città, bonus, regione);
		Giocatore venditore=new Giocatore("A");
		venditore.setPunteggioRicchezza(3);
		venditore.getTesserePermesso().add(tessera);
		Giocatore acquirente=new Giocatore("A");
		acquirente.setPunteggioRicchezza(10);
		Offerta offerta = new Offerta(venditore, tessera, 1);
		
		assertTrue(tessera.acquista(acquirente, offerta));
		assertEquals(1, acquirente.getTesserePermesso().size());
		assertEquals(0, venditore.getTesserePermesso().size());
		assertEquals(4, venditore.getPunteggioRicchezza());
		assertEquals(9, acquirente.getPunteggioRicchezza());
		assertTrue(acquirente.getTesserePermesso().contains(tessera));
		assertTrue(!venditore.getTesserePermesso().contains(tessera));
	}
	
	@Test
	public void testAcquistaFalse() {
		TesseraPermesso tessera=new TesseraPermesso(città, bonus, regione);
		Giocatore venditore=new Giocatore("A");
		venditore.setPunteggioRicchezza(3);
		venditore.getTesserePermesso().add(tessera);
		Giocatore acquirente=new Giocatore("A");
		acquirente.setPunteggioRicchezza(10);
		Offerta offerta = new Offerta(venditore, tessera, 100);
		
		assertTrue(!tessera.acquista(acquirente, offerta));
		assertEquals(0, acquirente.getTesserePermesso().size());
		assertEquals(1, venditore.getTesserePermesso().size());
		assertEquals(3, venditore.getPunteggioRicchezza());
		assertEquals(10, acquirente.getPunteggioRicchezza());
		assertTrue(!acquirente.getTesserePermesso().contains(tessera));
		assertTrue(venditore.getTesserePermesso().contains(tessera));
	}

	@Test
	public void testPossiedeFalse() {
		TesseraPermesso tessera=new TesseraPermesso(città, bonus, regione);
		Giocatore venditore=new Giocatore("A");
		venditore.getTesserePermesso().add(new TesseraPermesso(città, Arrays.asList(new BonusAiutanti(1000)), regione));
		
		assertTrue(!tessera.possiede(venditore));
	}
	
	@Test
	public void testPossiedeTrue() {
		TesseraPermesso tessera=new TesseraPermesso(città, bonus, regione);
		Giocatore venditore=new Giocatore("A");
		venditore.getTesserePermesso().add(tessera);

		assertTrue(tessera.possiede(venditore));
	}
	
	@Test
	public void testEqualsObject() {
		TesseraPermesso tessera=new TesseraPermesso(città, bonus, regione);
		assertTrue(tessera.equals(tessera));
	}
	
	@Test
	public void testEqualsNull() {
		assertFalse(tessera.equals(null));
	}
	
	@Test
	public void testEqualsClass() {
		assertFalse(tessera.equals(new GUI()));
	}
	
	@Test
	public void testEqualsBonusDiversi() {
		TesseraPermesso tesseraPermesso=new TesseraPermesso(città, Arrays.asList(new BonusTesseraPermesso()), regione);	
		assertFalse(tessera.equals(tesseraPermesso));
	}
	
	@Test
	public void testEqualsCittàDiversi() {
		TesseraPermesso tesseraPermesso=new TesseraPermesso(Arrays.asList(gameState.getRegioni().get(0).getCittàRegione().get(3)), bonus, regione);	
		assertFalse(tessera.equals(tesseraPermesso));
	}
	
	@Test
	public void testEqualsRegioneDiversi() {
		TesseraPermesso tesseraPermesso=new TesseraPermesso(città, bonus, gameState.getRegioni().get(2));	
		assertFalse(tessera.equals(tesseraPermesso));
	}
	
	@Test
	public void marketableDTO(){
		assertTrue(tessera.instance() instanceof TesseraPermessoDTO);
	}

}