package server.model.azioni;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class ChatTest {

	static GameState gameState;
	static Giocatore giocatore;
	static List<Notify> notify;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		notify = new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		giocatori.add(new Giocatore("B"));
		giocatori.add(new Giocatore("C"));
		gameState=new GameState();
		Observer<Notify> observer = new Observer<Notify>() {

			@Override
			public void update(Notify c) {
				notify.add(c);
			}
		};
		gameState.registerObserver(observer);
		gameState.start(giocatori, "mappa1");
	}

	@Test
	public void testEseguiAzione() {
		notify.clear();
		Chat chat=new Chat();
		chat.setMessaggio("Ciao a tutti");
		chat.eseguiAzione(gameState);
		
		assertEquals(1, notify.size());
		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test
	public void testGetAzioneDTO() {
		assertTrue(new Chat().getAzioneDTO()==null);
	}

	@Test
	public void testGetMessaggio() {
		Chat chat=new Chat();
		String messaggio="Ciao a tutti";
		chat.setMessaggio(messaggio);
		assertTrue(messaggio==chat.getMessaggio());
	}

	@Test(expected=NullPointerException.class)
	public void testSetMessaggio() {
		Chat chat=new Chat();
		chat.setMessaggio(null);
	}

}
