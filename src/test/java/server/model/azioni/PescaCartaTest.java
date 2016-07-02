package server.model.azioni;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.PescaCartaDTO;
import server.model.azioni.PescaCarta;
import server.model.game.CartaPolitica;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class PescaCartaTest {
	
	static GameState gameState;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");

	}

	@Test
	public void testEseguiAzione() {
		PescaCarta pesca=new PescaCarta();
		CartaPolitica carta=gameState.getMazzoCartePolitica().getCarte().get(0);
		pesca.eseguiAzione(gameState);
		int numeroCarte=gameState.getGiocatoreCorrente().getCartePolitica().size();
		assertTrue(carta==gameState.getGiocatoreCorrente().getCartePolitica().get(numeroCarte-1));
	}
	
	@Test
	public void testAzioneDTO() {
		assertTrue(new PescaCarta().getAzioneDTO() instanceof PescaCartaDTO);
	}
	
	
	

}
