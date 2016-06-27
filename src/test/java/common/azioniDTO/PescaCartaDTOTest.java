package common.azioniDTO;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.PescaCartaDTO;
import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import server.model.azioni.PescaCarta;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class PescaCartaDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");

		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testAccept(){
		int numeroCarte=gameState.getGiocatoreCorrente().getCartePolitica().size();
		PescaCartaDTO pescaDTO=new PescaCartaDTO();
		PescaCarta azioneParser=(PescaCarta) pescaDTO.accept(visitor);
		azioneParser.eseguiAzione(gameState);
		assertTrue(gameState.getGiocatoreCorrente().getCartePolitica().size()==numeroCarte+1);
	}

	@Test
	public void testToString() {
		PescaCartaDTO pescaDTO=new PescaCartaDTO();
	}
	
}
