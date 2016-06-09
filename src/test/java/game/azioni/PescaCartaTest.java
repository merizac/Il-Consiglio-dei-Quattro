package game.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.CartaPolitica;
import game.GameState;
import game.Giocatore;
import utility.exception.AzioneNonEseguibile;

public class PescaCartaTest {
	
	static GameState gameState;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);

	}

	@Test
	public void testEseguiAzione() throws AzioneNonEseguibile {
		PescaCarta pesca=new PescaCarta();
		CartaPolitica carta=gameState.getMazzoCartePolitica().getCarte().get(0);
		pesca.eseguiAzione(gameState);
		int numeroCarte=gameState.getGiocatoreCorrente().getCartePolitica().size();
		assertTrue(carta==gameState.getGiocatoreCorrente().getCartePolitica().get(numeroCarte-1));
	}

}
