package game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import bonus.Bonus;
import bonus.BonusAiutanti;

public class CittàBonusTest {

	
	
	
	
	
	
	
	
	
	
	
	
	//test sul costruttore per l'aggiunta al colore città
	
	ArrayList<Bonus> bonus=new ArrayList<Bonus>();
	
	CittàBonus città=new CittàBonus("NomeCittà", null, new ColoreCittà("ColoreCittà", null), bonus);
	
	@Test
	public void testGetBonus() {
		bonus.add(new BonusAiutanti(2));
		assertEquals(new BonusAiutanti(2), bonus);
	}

	@Test
	public void testSetBonus() {
		fail("Not yet implemented");
	}

}
