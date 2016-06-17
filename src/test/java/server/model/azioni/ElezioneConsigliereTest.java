package server.model.azioni;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.azioniPrincipali.ElezioneConsigliere;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ElezioneConsigliereTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
	}

	@Test
	public void testEseguiAzione() {
		ElezioneConsigliere elezioneConsigliere=new ElezioneConsigliere();
		Consigliere consigliere=gameState.getConsiglieri().get(0);
		Consigliere consigliereTolto=gameState.getRegioni().get(0).getBalcone().getConsigliere().element();
		
		elezioneConsigliere.setBalcone(gameState.getRegioni().get(0).getBalcone());
		elezioneConsigliere.setConsigliere(consigliere);
		elezioneConsigliere.eseguiAzione(gameState);
		
		System.out.println(gameState.getRegioni().get(0).getBalcone().getConsigliere());
		System.out.println(consigliere);
		System.out.println(consigliereTolto);
		
		assertEquals(14, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertTrue(gameState.getRegioni().get(0).getBalcone().getConsigliere().contains(consigliere));
		assertTrue(gameState.getConsiglieri().contains(consigliereTolto));
	}

	@Test(expected=NullPointerException.class)
	public void testSetRegione() {
		ElezioneConsigliere elezioneConsigliere=new ElezioneConsigliere();
		elezioneConsigliere.setBalcone(null);
	}

	@Test(expected=NullPointerException.class)
	public void testSetConsigliere() {
		ElezioneConsigliere elezioneConsigliere=new ElezioneConsigliere();
		elezioneConsigliere.setConsigliere(null);
	}

}
