package game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bonus.Bonus;
import bonus.BonusAiutanti;
import bonus.BonusAzionePrincipale;
import gameDTO.gameDTO.ColoreBonusDTO;

public class CittàBonusTest {

	static GameState gameState;
	
	@Test(expected=NullPointerException.class)
	public void controlloCreazioneCittàConBonus(){
		CittàBonus città=new CittàBonus("Nome", gameState.getRegioni().get(0), new ColoreCittà("c", null), null);
	}
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
	}
	
	@Test
	public void costrutoreAggiungeLeNuoveCittàAlColoreCittà(){
		
		Regione regione=gameState.getRegioni().get(0);
		ArrayList<Città> città=new ArrayList<Città>(gameState.getCittà());
		ColoreCittà colore=(ColoreCittà) città.get(0).getColoreCittà();
		ArrayList<Bonus> bonus=new ArrayList<>();
		bonus.add(new BonusAiutanti(3));
		CittàBonus nuovaCittàBonus=new CittàBonus("NOME", regione, colore, bonus);
		assertTrue(colore.getCittà().contains(nuovaCittàBonus));
	}	
		
		
	
	
	//test sul costruttore per l'aggiunta al colore città
	
	
	@Test
	public void testGetBonus() {
		ArrayList<Bonus> bonus=new ArrayList<Bonus>();
		CittàBonus città=new CittàBonus("NomeCittà", gameState.getRegioni().get(0), new ColoreCittà("ColoreCittà", null), bonus);
		bonus.add(new BonusAiutanti(2));
		assertEquals(bonus, città.getBonus());
	}

	@Test
	public void testSetBonus() {
		ArrayList<Bonus> bonus=new ArrayList<Bonus>();
		CittàBonus città=new CittàBonus("NomeCittà", gameState.getRegioni().get(0), new ColoreCittà("ColoreCittà", null), bonus);
		bonus.add(new BonusAiutanti(2));
		bonus.add(new BonusAzionePrincipale(1));
		città.setBonus(bonus);
		assertEquals(bonus, città.getBonus());
	}

}
