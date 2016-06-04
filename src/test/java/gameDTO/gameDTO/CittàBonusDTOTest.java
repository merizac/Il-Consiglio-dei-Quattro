package gameDTO.gameDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import bonus.Bonus;
import bonus.BonusAiutanti;
import game.Città;
import game.CittàBonus;
import game.Colore;
import game.Emporio;
import game.GameState;
import game.Giocatore;

public class CittàBonusDTOTest {
	static GameState gameState;
	static CittàBonus città;
	static CittàBonusDTO cittàDTO;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		città=(CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(0);
		città.aggiungiEmporio(new Emporio(new Colore("A")));
		città.aggiungiEmporio(new Emporio(new Colore("B")));
		
		cittàDTO=new CittàBonusDTO();
	}

	@Test
	public void testInizializzaCittàBonus() {
		cittàDTO.inizializza(città);
		assertEquals(città.getNome(), cittàDTO.getNome());
		assertEquals(città.getColoreCittà().getColore(), cittàDTO.getColoreDTO().getColore());
		assertEquals(città.getEmpori().size(), cittàDTO.getEmpori().size());
		assertEquals(città.getBonus(), cittàDTO.getBonus());
	}


	@Test
	public void testGetBonus() {
		CittàBonusDTO c=new CittàBonusDTO();
		ArrayList<Bonus> bonus=new ArrayList<>();
		bonus.add(new BonusAiutanti(2));
		c.setBonus(bonus);
		
		assertEquals(bonus, c.getBonus());
	}

	@Test
	public void testSetBonus() {
		CittàBonusDTO c=new CittàBonusDTO();
		ArrayList<Bonus> bonus=new ArrayList<>();
		bonus.add(new BonusAiutanti(2));
		c.setBonus(bonus);
		
		assertEquals(bonus, c.getBonus());
	}
}
