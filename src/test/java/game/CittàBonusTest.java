package game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import bonus.Bonus;
import bonus.BonusAiutanti;
import mapping.gameToMap.ColoreBonusDTO;

public class CittàBonusTest {

	static GameState gameState;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		
	}
	
	@Test
	public void costrutoreAggiungeCittàAlColore(){
		ArrayList<Città> città=new ArrayList<Città>(gameState.getCittà());
		ArrayList<Città> cittàStessoColore=new ArrayList<Città>();
		ColoreCittà colore=(ColoreCittà) città.get(0).getColoreCittà();
		for(Città c:città){
			if(c.getColoreCittà().equals(colore)){
				cittàStessoColore.add(c);
			}
			System.out.println("CittàStessoColore: "+cittàStessoColore);
			int i=0;
			for (Città cit:cittàStessoColore){
				assertEquals(colore.getCittà().get(i), cit);
				i++;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	//test sul costruttore per l'aggiunta al colore città
	
	ArrayList<Bonus> bonus=new ArrayList<Bonus>();
	
	CittàBonus città=new CittàBonus("NomeCittà", null, new ColoreCittà("ColoreCittà", null), bonus);
	
	@Test
	public void testGetBonus() {
		bonus.add(new BonusAiutanti(2));
		assertEquals(new BonusAiutanti(2), bonus);
	}

	@Test
	public void testSetBonus() {
		fail("Not yet implemented");
	}

}
