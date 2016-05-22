package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CittàTest {

	static Città città;
	
	@BeforeClass
	public static void init(){
		Regione regione=new Regione("mare", null, null, null);
		città=new Città("A", regione, null);
		
	}
	
	
	@Before
	public void testAggiungiEmporio() {
		System.out.println(città.aggiungiEmporio(new Emporio(new Colore("C"))));
		System.out.println(città.getEmpori());

	}

	@Test
	public void testEmporioColore() {
		assertTrue(città.emporioColore(new Colore("C")));
	}

}
