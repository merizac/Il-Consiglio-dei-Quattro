package server.model.game;

import static org.junit.Assert.*;

import org.junit.Test;

import common.gameDTO.AiutanteDTO;
import server.model.game.Aiutante;
import server.model.market.Offerta;

public class AiutanteTest {

	@Test(expected=IllegalArgumentException.class)
	public void testAiutanteNegativo() {
		new Aiutante(-5);
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

	@Test
	public void testAcquistaTrue() {
		Aiutante aiutante=new Aiutante(1);
		Giocatore acquirente=new Giocatore("A");
		acquirente.setAiutanti(new Aiutante(0));
		acquirente.setPunteggioRicchezza(10);
		Giocatore venditore=new Giocatore("A");
		venditore.setAiutanti(new Aiutante(3));
		venditore.setPunteggioRicchezza(0);
		Offerta offerta=new Offerta(venditore, aiutante, 1);
		aiutante.acquista(acquirente, offerta);
		
		assertEquals(2, venditore.getAiutanti().getAiutante());
		assertEquals(1, acquirente.getAiutanti().getAiutante());
		assertEquals(1, venditore.getPunteggioRicchezza());
		assertEquals(9, acquirente.getPunteggioRicchezza());
	}
	
	@Test
	public void testAcquistaFalse() {
		Aiutante aiutante=new Aiutante(1);
		Giocatore acquirente=new Giocatore("A");
		Giocatore venditore=new Giocatore("B");
		Offerta offerta=new Offerta(acquirente, aiutante, 5);
		venditore.setPunteggioRicchezza(3);
		acquirente.setPunteggioRicchezza(0);
		venditore.setAiutanti(new Aiutante(2));
		acquirente.setAiutanti(new Aiutante(3));

		assertTrue(!aiutante.acquista(acquirente, offerta));
		assertEquals(3, acquirente.getAiutanti().getAiutante());
		assertEquals(2, venditore.getAiutanti().getAiutante());
		assertEquals(3, venditore.getPunteggioRicchezza());
		assertEquals(0, acquirente.getPunteggioRicchezza());

	}

	@Test
	public void testPossiedeTrue() {
		Aiutante aiutante=new Aiutante(1);
		Giocatore venditore=new Giocatore("A");
		venditore.setAiutanti(new Aiutante(3));
		
		assertTrue(aiutante.possiede(venditore));
	}
	
	@Test
	public void testPossiedeFalse() {
		Aiutante aiutante=new Aiutante(1);
		Giocatore venditore=new Giocatore("A");
		venditore.setAiutanti(new Aiutante(0));
		
		assertTrue(!aiutante.possiede(venditore));
	}

	@Test
	public void testToString() {
		assertEquals("Aiutanti:2", new Aiutante(2).toString());
	}

	@Test
	public void testInstance() {
		assertTrue(new Aiutante(2).instance() instanceof AiutanteDTO);
	}

}
