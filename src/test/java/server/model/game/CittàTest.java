package server.model.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Città;
import server.model.game.Colore;
import server.model.game.Emporio;
import server.model.game.Regione;

public class CittàTest {

	static Città città;
	
	@BeforeClass
	public static void init(){
		Regione regione=new Regione("mare", null, null, null);
		città=new Città("A", regione, null);
		
	}
	
	
	@Before
	public void testAggiungiEmporio() {
		Emporio emporio=new Emporio(new Colore("C"));
		città.aggiungiEmporio(emporio);
		assertTrue(città.getEmpori().contains(emporio));
		assertEquals(1, città.getEmpori().size());
	}

	@Test
	public void testEmporioColore() {
		assertTrue(città.emporioColore(new Colore("C")));
	}
	
	@Test
	public void testEqualsCittàStessa(){
		assertTrue(città.equals(città));
	}
	
	@Test
	public void testEqualsNull(){
		assertFalse(città.equals(null));
	}
	
	@Test
	public void testClasseDiversa(){
		assertFalse(città.equals(new Object()));
	}
	
	@Test
	public void testEqualsCittàDiversaRegioneUguale(){
		Regione regione=new Regione("mare", null, null, null);
		Città cittàTest = new Città("cittàTest", regione, null);
		assertFalse(città.equals(cittàTest));
	}
	
	@Test
	public void testEqualsCittàUgualeRegioneDiversa(){
		Regione regione=new Regione("montagna", null, null, null);
		Città cittàTest = new Città(città.getNome(), regione, null);
		assertFalse(città.equals(cittàTest));
	}
	
	@Test
	public void testEqualsCittàUgualeRegioneUguale(){
		Regione regione=new Regione("mare", null, null, null);
		Città cittàTest = new Città(città.getNome(), regione, null);
		assertTrue(città.equals(cittàTest));
	}

}
