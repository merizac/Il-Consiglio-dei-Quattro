package server.model.game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import client.grafica.gui.GUI;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.Città;
import server.model.game.ColoreCittà;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ColoreCittàTest {

	static GameState gameState;
	static ColoreCittà colore;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
		colore=new ColoreCittà("A", new BonusPuntiVittoria(100));
	}
	
	@Test
	public void testGetCittà() {
		ArrayList<Città> cittàStessoColore=new ArrayList<Città>();
		ArrayList<Città> città=new ArrayList<Città>(gameState.getCittà());
		ColoreCittà colore=(ColoreCittà) città.get(0).getColoreCittà();

		for(Città c: gameState.getCittà()){
			if(c.getColoreCittà().equals(colore)){
				cittàStessoColore.add(c);
			}
		}

		for (Città c: cittàStessoColore){
			assertTrue(colore.getCittà().contains(c));
		}

		for (Città c: colore.getCittà()){
			assertTrue(cittàStessoColore.contains(c));
		}
	}

	@Test
	public void testGetBonusColore() {
		BonusPuntiVittoria bonus=new BonusPuntiVittoria(4);
		ColoreCittà coloreCittà=new ColoreCittà("Colore", bonus);
		assertEquals(bonus, coloreCittà.getBonusColore());
	}

	@Test
	public void testIsAssegnato() {
		BonusPuntiVittoria bonus=new BonusPuntiVittoria(4);
		ColoreCittà coloreCittà=new ColoreCittà("Colore", bonus);
		assertFalse(coloreCittà.isAssegnatoBonus());
	}

	@Test
	public void testEqualsObject() {
		assertTrue(colore.equals(colore));
	}
	
	@Test
	public void testEqualsNull() {
		assertFalse(colore.equals(null));
	}
	
	@Test
	public void testEqualsClass() {
		assertFalse(colore.equals(new GUI()));
	}
	
	@Test
	public void testEqualsBonusDiversi() {
		ColoreCittà coloreCittà=new ColoreCittà("A", new BonusPuntiVittoria(10));
		assertFalse(colore.equals(coloreCittà));
	}
	
	@Test
	public void testEqualsColoriDiversi() {
		ColoreCittà coloreCittà=new ColoreCittà("B", new BonusPuntiVittoria(10));
		assertFalse(colore.equals(coloreCittà));
	}
	
	@Test
	public void testEqualscoloreNull() {
		ColoreCittà coloreCittà=new ColoreCittà(null, new BonusPuntiVittoria(10));
		assertFalse(colore.equals(coloreCittà));
	}
	
	@Test
	public void testEqualsBonusNull() {
		ColoreCittà coloreCittà=new ColoreCittà("B", null);
		ColoreCittà coloreCittà1=new ColoreCittà("A", new BonusPuntiVittoria(10));

		assertFalse(coloreCittà.equals(coloreCittà1));
	}	
	
	@Test
	public void testEqualsCittàNull() {
		ColoreCittà coloreCittà=new ColoreCittà(null, new BonusPuntiVittoria(10));
		ColoreCittà coloreCittà1=new ColoreCittà("A", new BonusPuntiVittoria(10));

		assertFalse(coloreCittà.equals(coloreCittà1));
	}	
	
	@Test
	public void testEqualsCittàDiverse() {
		ColoreCittà coloreCittà=new ColoreCittà("b", new BonusPuntiVittoria(10));
		ColoreCittà coloreCittà1=new ColoreCittà("A", new BonusPuntiVittoria(10));

		assertFalse(coloreCittà.equals(coloreCittà1));
	}	
}
