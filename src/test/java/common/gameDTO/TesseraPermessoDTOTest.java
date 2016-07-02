package common.gameDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
		gameState.start(giocatori, "mappa1");
		
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
	public void testToString() {
		List<Città> c=new ArrayList<>();
		c.add(gameState.getRegioni().get(0).getCittàRegione().get(2));
		c.add(gameState.getRegioni().get(0).getCittàRegione().get(1));
		c.add(gameState.getRegioni().get(0).getCittàRegione().get(0));
		
		TesseraPermesso t=new TesseraPermesso(c, Arrays.asList(new BonusAiutanti(2)), gameState.getRegioni().get(0));
		TesseraPermessoDTO tDTO=new TesseraPermessoDTO();
		tDTO.inizializza(t);
		System.out.println(tDTO);
		assertEquals("TesseraPermesso  città:[Arkon, Burgen, Castrum], bonus:[BonusAiutanti 2]", tDTO.toString());
	}
	
	@Test 
	public void testOrdinaCittà(){
		List<Città> c=new ArrayList<>();
		c.add(gameState.getRegioni().get(0).getCittàRegione().get(2));
		c.add(gameState.getRegioni().get(0).getCittàRegione().get(1));
		c.add(gameState.getRegioni().get(0).getCittàRegione().get(0));
		
		TesseraPermesso t=new TesseraPermesso(c, Arrays.asList(new BonusAiutanti(2)), gameState.getRegioni().get(0));
		TesseraPermessoDTO tDTO=new TesseraPermessoDTO();
		tDTO.inizializza(t);
		List<String> città=tDTO.ordinaCittà();
		assertEquals(città.get(0),"Arkon");
		assertEquals(città.get(1),"Burgen");
		assertEquals(città.get(2),"Castrum");
	}


}
