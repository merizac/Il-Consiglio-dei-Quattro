package bonus;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import game.GameState;

public class BonusAzionePrincipaleTest {

	
	@Test
	public void testUsaBonus() throws IOException {
		GameState gameState = new GameState();
		boolean azionePrincipale = gameState.isBonusAzionePrincipale();
		BonusAzionePrincipale bonus= new BonusAzionePrincipale();
		bonus.usaBonus(gameState);
		assertFalse(azionePrincipale);
		assertTrue(gameState.isBonusAzionePrincipale());
	}


}
