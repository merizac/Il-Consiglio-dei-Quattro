package server.model.game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.PunteggioNobiltà;

public class PunteggioNobiltàTest {

	static int punti;
	static PunteggioNobiltà casella;
	static ArrayList<Bonus> bonus;

	@BeforeClass
	public static void init(){
		bonus=new ArrayList<Bonus>();
		punti=1;
		bonus.add(new BonusAiutanti(2));
		bonus.add(new BonusPuntiVittoria(4));
		
		casella=new PunteggioNobiltà(punti, bonus);
	}
	
	@Test
	public void testGetBonus() {
		assertEquals(bonus, casella.getBonus());
	}

	@Test
	public void testGetPuntiNobiltà() {
		assertEquals(punti, casella.getPuntiNobiltà());
	}

}
