package server.model.bonus;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import common.azioniDTO.AzioneAcquistoDTO;
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
	
	@Test
	public void testToString(){
		assertEquals("BonusAzionePrincipale", new BonusAzionePrincipale(1).toString());
	}

	@Test
	public void equalsObject(){
		BonusAzionePrincipale bonus=new BonusAzionePrincipale(1);
		assertTrue(bonus.equals(bonus));
	}
	
	@Test
	public void equalsNull(){
		BonusAzionePrincipale bonus=new BonusAzionePrincipale(1);
		assertFalse(bonus.equals(null));
	}
	
	@Test
	public void equalsclasseDiversa(){
		BonusAzionePrincipale bonus=new BonusAzionePrincipale(1);
		assertFalse(bonus.equals(new AzioneAcquistoDTO()));
	}
	
	@Test
	public void equalsdueOggettiFalse(){
		BonusAzionePrincipale bonus=new BonusAzionePrincipale(1);
		assertFalse(bonus.equals(new BonusAzionePrincipale(10)));
	}
	
	@Test
	public void equalsdueOggettiTrue(){
		BonusAzionePrincipale bonus=new BonusAzionePrincipale(1);
		assertTrue(bonus.equals(new BonusAzionePrincipale(1)));
	}	
}
