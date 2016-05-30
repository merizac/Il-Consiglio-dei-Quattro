package bonus;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

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
		Aiutante aiutantiPrima=new Aiutante(4);
		Giocatore giocatore = new Giocatore("nome");
		GameState gameState=new GameState();
		ArrayList<Giocatore> giocatori=new ArrayList<Giocatore>();
		giocatori.add(giocatore);
		gameState.start(giocatori);
		BonusAiutanti b=new BonusAiutanti(3);
		b.usaBonus(gameState);
		assertEquals(aiutantiPrima.getAiutante(), giocatore.getAiutanti().getAiutante());
		
	}



}
