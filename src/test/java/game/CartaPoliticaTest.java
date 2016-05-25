package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class CartaPoliticaTest {

	@Test
	public void testGetColore() {
		Colore colore=new Colore("C1");
		assertEquals("C1", colore.getColore());
	}

	@Test
	public void testAcquista() {
		fail("Not yet implemented");
	}

	@Test
	public void testPossiede() {
		fail("Not yet implemented");
	}

}
