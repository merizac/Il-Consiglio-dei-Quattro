package game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bonus.BonusPuntiVittoria;
import it.polimi.ingsw.cg17.Reader;

public class RegioneTest {

	static GameState gameState;
	static Regione regione;
	
	@BeforeClass
	public static void setUp() throws IOException{
		gameState=new GameState();
	}

	@Test
	public void testGetNome() {
		assertEquals("Mare", gameState.getRegioni().get(0).getNome());
	}

	@Test
	public void testGetMazzoTesserePermesso() {
		assertEquals(Mazzo.class, gameState.getRegioni().get(0).getMazzoTesserePermesso().getClass());
	}

	@Test
	public void testGetTesserePermessoScoperte() {
		assertEquals(TesseraPermesso.class, gameState.getRegioni().get(0).getTesserePermessoScoperte().get(0).getClass());
		assertTrue(gameState.getRegioni().get(0).getTesserePermessoScoperte().size()==2);
	}

	@Test
	public void testGetBonusRegione() {
		assertEquals(BonusPuntiVittoria.class, gameState.getRegioni().get(0).getBonusRegione().getClass());
	}

	@Test
	public void testGetBalcone() {
		assertEquals(Balcone.class, gameState.getRegioni().get(0).getBalcone().getClass());
	}

	@Test
	public void testGetCittàRegione() {
		ArrayList<Città> città=new ArrayList<Città>(gameState.getCittà());
		
		assertTrue(città.containsAll(gameState.getRegioni().get(0).getCittàRegione()));
	}

}
