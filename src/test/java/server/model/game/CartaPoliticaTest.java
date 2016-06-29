package server.model.game;

import static org.junit.Assert.*;

import org.junit.Test;

import common.gameDTO.CartaPoliticaDTO;
import server.model.game.Colore;
import server.model.market.Offerta;

public class CartaPoliticaTest {
	
	@Test
	public void testCartaPolitica() {
		Colore colore=new Colore("C1");
		assertEquals("C1", colore.getColore());
	}

	@Test
	public void testGetColore() {
		Colore colore=new Colore("C1");
		assertEquals("C1", colore.getColore());
	}
	
	@Test
	public void testToString() {
		assertEquals("A", new CartaPolitica(new Colore("A")).toString());
	}

	@Test
	public void testAcquistaTrue() {
		CartaPolitica carta=new CartaPolitica(new Colore("c"));
		Giocatore venditore=new Giocatore("A");
		venditore.setPunteggioRicchezza(3);
		venditore.getCartePolitica().add(carta);
		Giocatore acquirente=new Giocatore("A");
		acquirente.getCartePolitica().add(new CartaPolitica(new Colore("c1")));
		acquirente.setPunteggioRicchezza(10);
		Offerta offerta = new Offerta(venditore, carta, 1);
		
		assertTrue(carta.acquista(acquirente, offerta));
		assertEquals(2, acquirente.getCartePolitica().size());
		assertEquals(0, venditore.getCartePolitica().size());
		assertEquals(4, venditore.getPunteggioRicchezza());
		assertEquals(9, acquirente.getPunteggioRicchezza());
		assertTrue(acquirente.getCartePolitica().contains(carta));
		assertTrue(!venditore.getCartePolitica().contains(carta));
	}

	@Test
	public void testAcquistaFalse() {
		CartaPolitica carta=new CartaPolitica(new Colore("c"));
		Giocatore venditore=new Giocatore("A");
		venditore.setPunteggioRicchezza(3);
		venditore.getCartePolitica().add(new CartaPolitica(new Colore("c")));
		Giocatore acquirente=new Giocatore("A");
		acquirente.getCartePolitica().add(new CartaPolitica(new Colore("c1")));
		acquirente.setPunteggioRicchezza(0);
		Offerta offerta = new Offerta(venditore, carta, 10);
		
		assertTrue(!carta.acquista(acquirente, offerta));
		assertEquals(1, acquirente.getCartePolitica().size());
		assertEquals(1, venditore.getCartePolitica().size());
		assertEquals(3, venditore.getPunteggioRicchezza());
		assertEquals(0, acquirente.getPunteggioRicchezza());
	}

	@Test
	public void testPossiedeFalse() {
		CartaPolitica carta=new CartaPolitica(new Colore("c"));
		Giocatore venditore=new Giocatore("A");
		venditore.getCartePolitica().add(new CartaPolitica(new Colore("c1")));
		
		assertTrue(!carta.possiede(venditore));
	}
	
	@Test
	public void testPossiedeTrue() {
		CartaPolitica carta=new CartaPolitica(new Colore("c"));
		Giocatore venditore=new Giocatore("A");
		venditore.getCartePolitica().add(new CartaPolitica(new Colore("c")));
		
		assertTrue(carta.possiede(venditore));
	}
	
	@Test
	public void testinstance() {
		assertTrue(new CartaPolitica(new Colore("A")).instance() instanceof CartaPoliticaDTO);
	}

}
