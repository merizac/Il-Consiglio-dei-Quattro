package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameState;
import game.Giocatore;
import game.azioni.PescaCarta;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import utility.exception.AzioneNonEseguibile;

public class PescaCartaDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testAccept() throws AzioneNonEseguibile {
		int numeroCarte=gameState.getGiocatoreCorrente().getCartePolitica().size();
		PescaCartaDTO pescaDTO=new PescaCartaDTO();
		PescaCarta azioneParser=(PescaCarta) pescaDTO.accept(visitor);
		azioneParser.eseguiAzione(gameState);
		assertTrue(gameState.getGiocatoreCorrente().getCartePolitica().size()==numeroCarte+1);
	}

}
