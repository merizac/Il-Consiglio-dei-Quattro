package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class AiutanteTest {

	@Test(expected=IllegalArgumentException.class)
	public void testAiutanteNegativo() {
		Aiutante aiutante=new Aiutante(-5);
	}
	
	@Test
	public void testGetAiutante5() {
		Aiutante aiutante=new Aiutante(5);
		assertEquals(5,aiutante.getAiutante());
	}
	
	@Test
	public void testGetAiutante129() {
		Aiutante aiutante=new Aiutante(129);
		assertEquals(129,aiutante.getAiutante());
	}

	@Test
	public void testAggiungiAiutantiNumeriPositivi() {
		Aiutante aiutante= new Aiutante(3);
		aiutante.aggiungiAiutanti(2);
		assertEquals(5, aiutante.getAiutante());
		aiutante.aggiungiAiutanti(4);
		assertEquals(9, aiutante.getAiutante());		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAggiungiAiutantiNumeriNegativi() {
		Aiutante aiutante= new Aiutante(3);
		aiutante.aggiungiAiutanti(-3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAggiungiAiutantiNumeroNullo() {
		Aiutante aiutante= new Aiutante(3);
		aiutante.aggiungiAiutanti(0);
	}
	
	@Test
	public void testTogliAiutantiNumeriPositivi() {
		Aiutante aiutante= new Aiutante(3);
		aiutante.togliAiutanti(2);
		assertEquals(1, aiutante.getAiutante());		
	}
	
	@Test
	public void testTogliAiutantiNumeriPositiviConRisultatoNegativo() {
		Aiutante aiutante= new Aiutante(3);
		assertFalse(aiutante.togliAiutanti(7));		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTogliAiutantiNumeriNegativi() {
		Aiutante aiutante= new Aiutante(3);
		aiutante.togliAiutanti(-3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTogliAiutantiNumeroNullo() {
		Aiutante aiutante= new Aiutante(3);
		aiutante.togliAiutanti(0);
	}


	@Test
	public void testAcquista() {

	}

	@Test
	public void testPossiede() {

	}

}
