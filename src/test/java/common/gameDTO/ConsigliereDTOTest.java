package common.gameDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.gameDTO.ConsigliereDTO;
import server.model.game.Colore;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ConsigliereDTOTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
	}

	@Test
	public void testGetColoreConsigliere() {
		ConsigliereDTO consigliereDTO=new ConsigliereDTO();
		consigliereDTO.setColoreConsigliere("A");
		
		assertEquals("A", consigliereDTO.getColoreConsigliere());
	}

	@Test
	public void testSetColoreConsigliere() {
		ConsigliereDTO consigliereDTO=new ConsigliereDTO();
		consigliereDTO.setColoreConsigliere("A");
		
		assertEquals("A", consigliereDTO.getColoreConsigliere());
	}

	@Test
	public void testInizializza() {
		ConsigliereDTO consigliereDTO=new ConsigliereDTO();
		consigliereDTO.inizializza(new Consigliere(new Colore("A")));
		
		assertEquals("A", consigliereDTO.getColoreConsigliere());
	}

}
