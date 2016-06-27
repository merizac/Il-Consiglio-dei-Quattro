package common.azioniDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.IngaggioAiutanteDTO;
import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import server.model.azioni.azioniVeloci.IngaggioAiutante;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class IngaggioAiutanteDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(5);
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));;
		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testAccept() {
		IngaggioAiutanteDTO ingaggio=new IngaggioAiutanteDTO();
		IngaggioAiutante azioneParser=(IngaggioAiutante) ingaggio.accept(visitor);
		azioneParser.eseguiAzione(gameState);
		
		assertTrue(gameState.getGiocatoreCorrente().getPunteggioRicchezza()==2);
		assertTrue(gameState.getGiocatoreCorrente().getAiutanti().getAiutante()==2);
	}

	@Test
	public void testToString() {
		IngaggioAiutanteDTO ingaggio=new IngaggioAiutanteDTO();
		assertEquals("IngaggioAiutante  [V1]", ingaggio.toString());
	}
}
