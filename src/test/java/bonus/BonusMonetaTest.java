package bonus;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

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
		bonus=new BonusMoneta(100);
		giocatore=new Giocatore("giocatore");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(giocatore);
		gameState.start(giocatori);
		
	}
	
	@Test
	public void testUsaBonus() {
		bonus.usaBonus(gameState);
		assertEquals(10+100, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
	}

}
