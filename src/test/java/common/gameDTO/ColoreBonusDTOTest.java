package common.gameDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.gameDTO.ColoreBonusDTO;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.ColoreCittà;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ColoreBonusDTOTest {

	static ColoreCittà coloreCittà;
	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);

		coloreCittà=new ColoreCittà("A", new BonusPuntiVittoria(2));
	}

	@Test
	public void testGetBonusDTO() {
		ColoreBonusDTO colore=new ColoreBonusDTO();
		colore.setBonus(new BonusPuntiVittoria(3));
		
		assertTrue(colore.getBonusDTO().equals(new BonusPuntiVittoria(3)));		
	}
	
	@Test
	public void testInizializzaColoreCittà() {
		ColoreBonusDTO colore=new ColoreBonusDTO();
		colore.inizializza(coloreCittà);
		
		assertEquals(coloreCittà.getColore(), colore.getColore());
		assertTrue(coloreCittà.getBonusColore()==colore.getBonusDTO());
	}
	
	@Test
	public void testSetBonus() {
		ColoreBonusDTO colore=new ColoreBonusDTO();
		colore.setBonus(new BonusPuntiVittoria(3));
		
		assertTrue(colore.getBonusDTO().equals(new BonusPuntiVittoria(3)));
	}


}
