package game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.gameDTO.TesseraPermessoDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.game.Città;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;

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
		gameState.start(giocatori);
	
		città=new ArrayList<>();
		bonus=new ArrayList<>();
		città.add(gameState.getRegioni().get(0).getCittàRegione().get(0));
		regione=gameState.getRegioni().get(0);
		bonus.add(new BonusAiutanti(2));
		
		tessera=new TesseraPermesso(città, bonus, regione);
		gameState.getGiocatoreCorrente().aggiungiTesseraPermesso(tessera);
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
	public void testAcquista() {
		
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
	
	

}
