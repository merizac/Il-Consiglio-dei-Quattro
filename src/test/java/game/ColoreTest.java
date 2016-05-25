package game;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ColoreTest {

	static Colore colore;
	
	@BeforeClass
	public static void init() {
		colore=new Colore("prova");
	}

	@Test
	public void testGetColore() {
		assertEquals("prova", colore.getColore());
	}
}
