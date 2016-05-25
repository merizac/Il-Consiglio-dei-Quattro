package bonus;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import game.CartaPolitica;
import game.Colore;
import game.GameState;
import game.Giocatore;

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
		GameState gameState = new GameState();
		//ArrayList<CartaPolitica> carte = new ArrayList<CartaPolitica>();
		Giocatore giocatore = new Giocatore("nome");
		gameState.setGiocatoreCorrente(giocatore);
		giocatore.aggiungiCartaPolitica(new CartaPolitica(new Colore("1")));
		//int numCarte = carte.size();
		BonusCartePolitica bonus = new BonusCartePolitica(3);
		bonus.usaBonus(gameState);
		assertEquals(1+3, giocatore.getCartePolitica().size());
		
	}

}
