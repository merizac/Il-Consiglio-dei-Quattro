package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import server.model.azioni.Chat;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ChatDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	static Giocatore giocatore;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testAccept() {
		ChatDTO chatDTO=new ChatDTO();
		chatDTO.setMessaggio("ciao");
		Chat chat=(Chat) chatDTO.accept(visitor);
		assertEquals("ciao", chat.getMessaggio());
	}

	@Test
	public void testGetMessaggio() {
		ChatDTO chat=new ChatDTO();
		String stringa="ciao";
		chat.setMessaggio(stringa);
		
		assertTrue(stringa==chat.getMessaggio());
	}

	@Test(expected=NullPointerException.class)
	public void testSetMessaggio() {
		ChatDTO chat=new ChatDTO();
		chat.setMessaggio(null);
	}

}
