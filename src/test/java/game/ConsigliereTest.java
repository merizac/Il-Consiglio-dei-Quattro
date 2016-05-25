package game;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import game.query.GetConsiglieri;

public class ConsigliereTest {

	static Consigliere consigliere;
	
	@BeforeClass
	public static void init(){
		consigliere=new Consigliere(new Colore("consigliere"));
	}
	
	@Test
	public void testGetColore() {
		assertEquals(new Colore("consigliere"), consigliere.getColore());
	}

}
