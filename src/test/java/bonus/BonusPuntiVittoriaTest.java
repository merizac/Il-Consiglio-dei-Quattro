package bonus;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameState;
import game.Giocatore;

public class BonusPuntiVittoriaTest {
	
	static GameState gameState;
	static Bonus bonus;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		bonus=new BonusPuntiVittoria(4);
		Giocatore giocatore=new Giocatore("giocatore");
		giocatore.setPunteggioVittoria(12);
		gameState.setGiocatoreCorrente(giocatore);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void bonusPuntivittoriaNumeroNegativo() {
		Bonus bonus=new BonusPuntiVittoria(-3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void bonusPuntivittoriaNumeroNullo() {
		Bonus bonus=new BonusPuntiVittoria(0);
	}
	
	@Test
	public void testUsaBonus() {
		bonus.usaBonus(gameState);
		assertTrue(gameState.getGiocatoreCorrente().getPunteggioVittoria()==16);
	}

}
