package game;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Colore;
import server.model.game.Emporio;

public class EmporioTest {
	static Emporio emporio;
	
	@BeforeClass
	public static void init(){
		emporio=new Emporio(new Colore("emporio"));
	}
	
	@Test
	public void testGetColore() {
		assertEquals(new Colore("emporio"), emporio.getColore());
	}
}
