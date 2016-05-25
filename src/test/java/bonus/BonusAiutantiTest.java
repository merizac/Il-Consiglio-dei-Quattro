package bonus;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import game.Aiutante;

import game.GameState;
import game.Giocatore;

public class BonusAiutantiTest {

	int aiutanti;
	
	@Test
	public void testBonusAiutantiMaggioreDiZero() {
		aiutanti=10;
		BonusAiutanti bonus = new BonusAiutanti(aiutanti);
		assertEquals(aiutanti, bonus.getAiutanti());
	}
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testBonusAiutantiMinoreUgualeAZero(){
		aiutanti=-5;
		new BonusAiutanti(aiutanti);
		
	}
	
	
	@Test
	public void testUsaBonus() throws IOException {
		aiutanti=5;
		Aiutante aiutantiPrima=new Aiutante(3);
		Giocatore giocatore = new Giocatore("nome",null, null, aiutantiPrima, 0, 0, null, null);
		System.out.println(giocatore);
		GameState gameState=new GameState();
		gameState.setGiocatoreCorrente(giocatore);
		new BonusAiutanti(aiutanti);
		assertEquals(aiutantiPrima, giocatore.getAiutanti());
	
		
	}



}
