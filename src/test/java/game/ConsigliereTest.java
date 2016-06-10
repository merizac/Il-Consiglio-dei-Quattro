package game;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Colore;
import server.model.game.Consigliere;


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
