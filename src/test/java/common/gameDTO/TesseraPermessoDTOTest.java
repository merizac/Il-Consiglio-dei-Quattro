package common.gameDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import common.gameDTO.CittàDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.game.Città;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.TesseraPermesso;

public class TesseraPermessoDTOTest {
	
	static GameState gameState;
	static TesseraPermesso tesseraPermesso;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		tesseraPermesso=gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(0);
	}

	@Test
	public void testGetCittà() {
		Set<CittàDTO> città=new HashSet();
		CittàDTO c=new CittàDTO();
		c.inizializza(gameState.getRegioni().get(0).getCittàRegione().get(0));
		città.add(c);
		TesseraPermessoDTO tesseraDTO=new TesseraPermessoDTO();
		tesseraDTO.setCittà(città);;
		
		assertTrue(città.size()==tesseraDTO.getCittà().size());
	}

	@Test
	public void testSetCittà() {
		Set<CittàDTO> città=new HashSet();
		CittàDTO c=new CittàDTO();
		c.inizializza(gameState.getRegioni().get(0).getCittàRegione().get(0));
		città.add(c);
		TesseraPermessoDTO tesseraDTO=new TesseraPermessoDTO();
		tesseraDTO.setCittà(città);;
		
		assertTrue(città.size()==tesseraDTO.getCittà().size());
	}

	@Test
	public void testGetBonus() {
		ArrayList<Bonus> bonus=new ArrayList<>();
		BonusAiutanti b=new BonusAiutanti(2);
		bonus.add(b);
		TesseraPermessoDTO tesseraDTO=new TesseraPermessoDTO();
		tesseraDTO.setBonus(bonus);
		
		assertTrue(b==tesseraDTO.getBonus().get(0));
	}

	@Test
	public void testSetBonus() {
		ArrayList<Bonus> bonus=new ArrayList<>();
		BonusAiutanti b=new BonusAiutanti(2);
		bonus.add(b);
		TesseraPermessoDTO tesseraDTO=new TesseraPermessoDTO();
		tesseraDTO.setBonus(bonus);
		
		assertTrue(b==tesseraDTO.getBonus().get(0));
	}

	@Test
	public void testInizializza() {
		TesseraPermessoDTO tesseraDTO=new TesseraPermessoDTO();
		tesseraDTO.inizializza(tesseraPermesso);
		
		assertEquals(tesseraPermesso.getCittà().size(), tesseraDTO.getCittà().size());
		assertTrue(tesseraPermesso.getBonus()==tesseraDTO.getBonus());
	}

	@Test
	public void testCreaMarketable() {

	}

	@Test
	public void testCreaMarketableDTO() {

	}

}
