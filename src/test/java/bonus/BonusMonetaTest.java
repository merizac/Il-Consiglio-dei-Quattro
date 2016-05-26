package bonus;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameState;
import game.Giocatore;

public class BonusMonetaTest {

	static Giocatore giocatore;
	static GameState gameState;
	static Bonus bonus;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		bonus=new BonusMoneta(2);
		giocatore=new Giocatore("giocatore");
		giocatore.setPunteggioRicchezza(3);
		gameState.setGiocatoreCorrente(giocatore);
		
	}
	
	@Test
	public void testUsaBonus() {
		bonus.usaBonus(gameState);
		assertEquals(5, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
	}

}
