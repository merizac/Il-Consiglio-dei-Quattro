package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.SecondaAzionePrincipaleDTO;
import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import server.model.azioni.azioniVeloci.SecondaAzionePrincipale;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class SecondaAzionePrincipaleDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(3));
		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testAccept() {
		SecondaAzionePrincipaleDTO azione=new SecondaAzionePrincipaleDTO();
		SecondaAzionePrincipale azioneParser=(SecondaAzionePrincipale) azione.accept(visitor);
		azioneParser.eseguiAzione(gameState);
		assertTrue(0==gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
	}

	@Test
	public void testToString() {
		SecondaAzionePrincipaleDTO azione=new SecondaAzionePrincipaleDTO();
		assertEquals("Seconda azione Principale  [V4]", azione.toString());
	}
}
