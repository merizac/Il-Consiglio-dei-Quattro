package bonus;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.GameState;
import game.Giocatore;
import game.PunteggioNobiltà;

public class BonusPuntiNobiltàTest {

/*	static GameState gameState;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		Bonus bonus=new BonusPuntiNobiltà(2);
		Giocatore giocatore=new Giocatore("giocatore");
		giocatore.setPunteggioNobiltà(gameState.getPlanciaRe().getPercorsoNobiltà().get(0));
		gameState.setGiocatoreCorrente(giocatore);
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
		GameState gameState=new GameState();
		Bonus bonus=new BonusPuntiNobiltà(2);
		Giocatore giocatore=new Giocatore("giocatore");
		giocatore.setPunteggioNobiltà(gameState.getPlanciaRe().getPercorsoNobiltà().get(0));
		gameState.setGiocatoreCorrente(giocatore);
		PunteggioNobiltà casella=gameState.getPlanciaRe().getPercorsoNobiltà().get(2);
		bonus.usaBonus(gameState);
		
		assertTrue(casella==gameState.getGiocatoreCorrente().getPunteggioNobiltà());
	}

	
	@Test
	public void testUsaBonusChePortaAfinePercorso() throws IOException{
		GameState gameState1=new GameState();
		Giocatore giocatore=new Giocatore("giocatore");
		giocatore.setPunteggioNobiltà(gameState1.getPlanciaRe().getPercorsoNobiltà().get(2));
		gameState1.setGiocatoreCorrente(giocatore);

		Bonus bonus1=new BonusPuntiNobiltà(100);
		PunteggioNobiltà casella=gameState1.getPlanciaRe().getPercorsoNobiltà().get(gameState1.getPlanciaRe().getPercorsoNobiltà().size()-1);

		bonus1.usaBonus(gameState1);
		
		assertTrue(casella==gameState1.getGiocatoreCorrente().getPunteggioNobiltà());
	}*/
	
	
	static GameState gameState;
	static Giocatore giocatore;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		giocatore=new Giocatore("giocatore");
		giocatore.setPunteggioNobiltà(gameState.getPlanciaRe().getPercorsoNobiltà().get(0));
		gameState.setGiocatoreCorrente(giocatore);
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
}