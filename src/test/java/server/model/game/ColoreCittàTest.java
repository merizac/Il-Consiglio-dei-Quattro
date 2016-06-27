package server.model.game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.bonus.BonusAiutanti;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.Città;
import server.model.game.ColoreCittà;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ColoreCittàTest {

	static GameState gameState;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
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

}
