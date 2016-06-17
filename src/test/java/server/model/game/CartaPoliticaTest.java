package server.model.game;

import static org.junit.Assert.*;

import org.junit.Test;

import server.model.game.Colore;

public class CartaPoliticaTest {

	@Test
	public void testGetColore() {
		Colore colore=new Colore("C1");
		assertEquals("C1", colore.getColore());
	}

	@Test
	public void testAcquista() {

	}

	@Test
	public void testPossiede() {

	}

}
