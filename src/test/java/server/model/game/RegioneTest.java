package server.model.game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import server.model.bonus.BonusPuntiVittoria;
import server.model.game.Balcone;
import server.model.game.Città;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Mazzo;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;

public class RegioneTest {

	static GameState gameState;
	static Regione regione;
	
	@BeforeClass
	public static void setUp() throws IOException{
		gameState=new GameState();
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState.start(giocatori, "mappa1");
		regione=gameState.getRegioni().get(0);
	}

	@Test
	public void testGetNome() {
		assertEquals("Mare", regione.getNome());
	}

	@Test
	public void testGetMazzoTesserePermesso() {
		assertEquals(Mazzo.class, regione.getMazzoTesserePermesso().getClass());
	}

	@Test
	public void testGetTesserePermessoScoperte() {
		assertEquals(TesseraPermesso.class, regione.getTesserePermessoScoperte().get(0).getClass());
		assertTrue(gameState.getRegioni().get(0).getTesserePermessoScoperte().size()==2);
	}

	@Test
	public void testGetBonusRegione() {
		assertEquals(BonusPuntiVittoria.class, regione.getBonusRegione().getClass());
	}

	@Test
	public void testGetBalcone() {
		assertEquals(Balcone.class, regione.getBalcone().getClass());
	}

	@Test
	public void testGetCittàRegione() {
		ArrayList<Città> città=new ArrayList<Città>(gameState.getCittà());
		
		assertTrue(città.containsAll(regione.getCittàRegione()));
	}
	
	@Test
	public void testEqualsRegioneStessa(){
		assertTrue(regione.equals(regione));
	}
	
	@Test
	public void testEqualsRegioneNull(){
		assertFalse(regione.equals(null));
	}
	
	@Test
	public void testEqualsObject(){
		assertFalse(regione.equals(new Object()));
	}
	
	@Test
	public void testEqualsRegione(){
		Regione regioneTest = new Regione(regione.getNome(), 
				regione.getMazzoTesserePermesso(), regione.getBonusRegione(), regione.getBalcone());
		assertEquals(regione, regioneTest);
	}

}
