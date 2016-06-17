package server.model.game;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Colore;

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
