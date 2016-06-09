package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Aiutante;
import game.GameState;
import game.Giocatore;
import game.azioni.SecondaAzionePrincipale;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;

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

}
