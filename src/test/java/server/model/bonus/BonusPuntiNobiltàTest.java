package server.model.bonus;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.model.bonus.Bonus;
import server.model.bonus.BonusPuntiNobiltà;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.PunteggioNobiltà;

public class BonusPuntiNobiltàTest {

	static GameState gameState;
	static Giocatore giocatore;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
		giocatore=gameState.getGiocatoreCorrente();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void usaBonusNonPuòRicevereNumeroNegativo() {
		BonusPuntiNobiltà bonus=new BonusPuntiNobiltà(-2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void usaBonusNonPuòRicevereNumeroNullo() {
		BonusPuntiNobiltà bonus=new BonusPuntiNobiltà(0);
	}
	
	@Test
	public void testUsaBonus() throws IOException{
		Bonus bonus=new BonusPuntiNobiltà(2);
		PunteggioNobiltà casella=gameState.getPlanciaRe().getPercorsoNobiltà().get(2);
		bonus.usaBonus(gameState);
		
		assertTrue(casella==gameState.getGiocatoreCorrente().getPunteggioNobiltà());
	}

	
	@Test
	public void testUsaBonusChePortaAfinePercorso() throws IOException{
		Bonus bonus=new BonusPuntiNobiltà(100);
		PunteggioNobiltà casella=gameState.getPlanciaRe().getPercorsoNobiltà().get(gameState.getPlanciaRe().getPercorsoNobiltà().size()-1);

		bonus.usaBonus(gameState);
		
		assertTrue(casella==gameState.getGiocatoreCorrente().getPunteggioNobiltà());
	}
	
	@Test
	public void testGetPuntiNobiltà() {
		BonusPuntiNobiltà bonus=new BonusPuntiNobiltà(2);
		assertEquals(2, bonus.getPuntiNobiltà());
	}
}