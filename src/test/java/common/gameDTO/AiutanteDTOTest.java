package common.gameDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.gameDTO.AiutanteDTO;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.market.Marketable;

public class AiutanteDTOTest {
	
	static GameState gameState;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
	}

	@Test(expected=IllegalArgumentException.class)
	public void numeroAiutantiNegativoONullo() {
		AiutanteDTO aiutante=new AiutanteDTO(-2);	
	}

	@Test
	public void testGetAiutanti() {
		AiutanteDTO aiutante=new AiutanteDTO(3);	
		assertEquals(3, aiutante.getAiutanti());
	}

	@Test
	public void testCreaMarketable() {
		AiutanteDTO aiutante=new AiutanteDTO(1);
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(3));
		
		assertTrue(aiutante.creaMarketable(gameState.getGiocatoreCorrente()) instanceof Marketable);
	}

}
