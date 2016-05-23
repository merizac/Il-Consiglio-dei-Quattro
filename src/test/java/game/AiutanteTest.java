package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AiutanteTest {
	
	Aiutante aiutante=new Aiutante(10);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAggiungiAiutanti() {
		int incremento=5;
		aiutante.aggiungiAiutanti(incremento);
		assertTrue(aiutante.getAiutante()==15);
	}

	@Test
	public void testTogliAiutanti() {
		int decremento=6;
		int decremento2=12;
		aiutante.togliAiutanti(decremento);
		assertTrue(aiutante.getAiutante()==4);
		assertFalse(aiutante.togliAiutanti(decremento2));
	}

}
