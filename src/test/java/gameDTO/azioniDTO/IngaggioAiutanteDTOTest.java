package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.GameState;
import game.Giocatore;
import game.azioni.IngaggioAiutante;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;

public class IngaggioAiutanteDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(5);
		
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

}
