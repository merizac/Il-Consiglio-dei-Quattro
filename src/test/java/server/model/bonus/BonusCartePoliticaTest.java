package server.model.bonus;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import server.model.bonus.BonusCartePolitica;
import server.model.game.CartaPolitica;
import server.model.game.Colore;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class BonusCartePoliticaTest {

	int cartePolitica;
	
	@Test
	public void testCarteMaggioreDiZero(){
		cartePolitica=10;
		BonusCartePolitica bonus = new BonusCartePolitica(cartePolitica);
		assertEquals(cartePolitica, bonus.getCartePolitica());
	}
	

	@Test (expected=java.lang.IllegalArgumentException.class)
	public void testCarteMinoreDiZero(){
		cartePolitica=-3;
		new BonusCartePolitica(cartePolitica);
	}
	
	@Test
	public void testUsaBonus() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		GameState gameState = new GameState();
		Giocatore giocatore = new Giocatore("nome");
		giocatori.add(giocatore);
		gameState.start(giocatori);
		BonusCartePolitica bonus = new BonusCartePolitica(3);
		bonus.usaBonus(gameState);
		assertEquals(6+3, giocatore.getCartePolitica().size());
		
	}

}
