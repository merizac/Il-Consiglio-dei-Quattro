package server.model.bonus;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import server.model.bonus.BonusAzionePrincipale;
import server.model.game.GameState;

public class BonusAzionePrincipaleTest {

	
	@Test
	public void testUsaBonus() throws IOException {
		GameState gameState = new GameState();
		boolean azionePrincipale = gameState.isBonusAzionePrincipale();
		BonusAzionePrincipale bonus= new BonusAzionePrincipale(1);
		bonus.usaBonus(gameState);
		assertFalse(azionePrincipale);
		assertTrue(gameState.isBonusAzionePrincipale());
	}


}
