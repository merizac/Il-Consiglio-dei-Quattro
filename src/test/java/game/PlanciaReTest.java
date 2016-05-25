package game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import bonus.Bonus;
import bonus.BonusAiutanti;
import bonus.BonusCartePolitica;

public class PlanciaReTest {

	static Balcone balconeRe;
	static ArrayList<Bonus> bonusPremioRe;
	static ArrayList<PunteggioNobiltà> percorsoNobiltà;
	static PlanciaRe planciaRe;
	
	@BeforeClass
	public static void init(){
		ArrayList<Consigliere> consiglieri=new ArrayList<Consigliere>();
		consiglieri.add(new Consigliere(new Colore("1")));
		consiglieri.add(new Consigliere(new Colore("2")));
		consiglieri.add(new Consigliere(new Colore("3")));
		consiglieri.add(new Consigliere(new Colore("4")));
		balconeRe=new Balcone(consiglieri);
		
		bonusPremioRe=new ArrayList<Bonus>();
		bonusPremioRe.add(new BonusAiutanti(2));
		bonusPremioRe.add(new BonusCartePolitica(1));
		
		percorsoNobiltà=new ArrayList<PunteggioNobiltà>();
		percorsoNobiltà.add(new PunteggioNobiltà(2, bonusPremioRe));
		
		planciaRe=new PlanciaRe(balconeRe, bonusPremioRe, percorsoNobiltà);
	}
	
	@Test
	public void testGetBalconeRe() {
		assertEquals(balconeRe, planciaRe.getBalconeRe());
	}

	@Test
	public void testGetBonusPremioRe() {
		assertEquals(bonusPremioRe, planciaRe.getBonusPremioRe());
	}

	@Test
	public void testGetPercorsoNobiltà() {
		assertEquals(percorsoNobiltà, planciaRe.getPercorsoNobiltà());
	}

}
