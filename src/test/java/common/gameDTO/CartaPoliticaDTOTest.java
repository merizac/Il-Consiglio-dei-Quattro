package common.gameDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.gameDTO.CartaPoliticaDTO;
import server.model.game.Aiutante;
import server.model.game.CartaPolitica;
import server.model.game.Colore;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.market.Marketable;

public class CartaPoliticaDTOTest {

	CartaPoliticaDTO cartaPoliticaDTO;
	static CartaPolitica cartaPolitica;
	static Colore colore;
	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");

		colore=new Colore("A");
		cartaPolitica=new CartaPolitica(colore);
	}

	@Test
	public void testGetColore() {
		assertEquals("A", cartaPoliticaDTO.getColore());
	}

	@Test
	public void testSetColore() {
		cartaPoliticaDTO.setColore("B");
		assertEquals("B", cartaPoliticaDTO.getColore());
		cartaPoliticaDTO.setColore("A");
	}

	@Before
	public void testInizializza() {
		cartaPoliticaDTO=new CartaPoliticaDTO();
		cartaPoliticaDTO.inizializza(cartaPolitica);
	}

	@Test
	public void testCreaMarketable() {

		assertTrue(cartaPoliticaDTO.creaMarketable(gameState.getGiocatoreCorrente()) instanceof Marketable);
	}

}
