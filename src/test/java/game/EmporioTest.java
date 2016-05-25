package game;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

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
