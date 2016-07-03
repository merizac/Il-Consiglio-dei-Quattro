package server.model.bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.azioniBonus.BonusTesseraPermessoN;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class BonusTesseraPermessoTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();

		gameState.start(giocatori, "mappa1");
	}

	@Test
	public void testUsaBonus() {
		BonusTesseraPermesso bonus=new BonusTesseraPermesso();

		bonus.usaBonus(gameState);

		assertTrue(gameState.getGiocatoreCorrente().getBonusNobiltà().get(0).equals(bonus.getAzioneBonus()));
		assertEquals(1, gameState.getGiocatoreCorrente().getBonusNobiltà().size());

	}

	@Test
	public void testGetAzioneBonus() {
		BonusTesseraPermesso bonus=new BonusTesseraPermesso();
		BonusTesseraPermessoN bonusN=new BonusTesseraPermessoN();
		assertEquals(bonusN, bonus.getAzioneBonus());
	}

}
