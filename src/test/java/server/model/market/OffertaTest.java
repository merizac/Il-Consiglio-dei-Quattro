package server.model.market;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class OffertaTest {

	static GameState gameState;
	Offerta offerta;
	Giocatore venditore;
	Aiutante marketable;
	int prezzo;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
	}

	@Before
	public void testOfferta() {
		venditore=new Giocatore("A");
		marketable=new Aiutante(1);
		prezzo=2;
		offerta=new Offerta(venditore, marketable, prezzo);
	}

	@Test
	public void testGetVenditore() {
		assertTrue(venditore==offerta.getVenditore());
	}

	@Test
	public void testGetMarketable() {
		assertTrue(marketable==offerta.getMarketable());
	}

	@Test
	public void testGetPrezzo() {
		assertTrue(prezzo==offerta.getPrezzo());

	}

	@Test(expected=NullPointerException.class)
	public void testVenditoreNull() {
		new Offerta(null, marketable, prezzo);
	}

	@Test(expected=NullPointerException.class)
	public void testMarketableNull() {
		new Offerta(venditore, null, prezzo);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrezzoNull() {
		new Offerta(venditore, marketable, -1);
	}

	

}
