package game.azioni;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.azioniVeloci.IngaggioAiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class IngaggioAiutanteTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
	}

	@Test
	public void testEseguiAzione() {
		IngaggioAiutante ingaggio=new IngaggioAiutante();
		int aiutanti=gameState.getGiocatoreCorrente().getAiutanti().getAiutante();
		
		ingaggio.eseguiAzione(gameState);
		
		assertEquals(aiutanti+1, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
	}

}
