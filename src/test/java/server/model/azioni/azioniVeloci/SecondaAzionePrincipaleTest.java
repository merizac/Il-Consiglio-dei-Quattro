package server.model.azioni.azioniVeloci;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.SecondaAzionePrincipaleDTO;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.macchinaStati.StartEnd;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class SecondaAzionePrincipaleTest {

	static GameState gameState;
	static List<Notify> notify;
	
	@BeforeClass
	public static void init() throws IOException{
		notify = new ArrayList<>();
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
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
	public void testEseguiAzioneConAiutantiMaggiori2() {
		SecondaAzionePrincipale azione=new SecondaAzionePrincipale();
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(10));
		notify.clear();
		
		azione.eseguiAzione(gameState);

		System.out.println(notify);
		assertTrue(gameState.getStato() instanceof StartEnd);
		assertEquals(10-3, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
		assertEquals(4, notify.size());
	}
	
	@Test
	public void testEseguiAzioneConAiutantiMinori2() {
		SecondaAzionePrincipale azione=new SecondaAzionePrincipale();
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));
		notify.clear();
		
		azione.eseguiAzione(gameState);

		assertTrue(gameState.getStato() instanceof StartEnd);
		assertEquals(1, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
		assertEquals(1, notify.size());
		assertTrue(notify.get(0) instanceof MessageNotify);
		
	}

	@Test
	public void testGetAzioneDTO() {
		assertTrue(new SecondaAzionePrincipale().getAzioneDTO() instanceof SecondaAzionePrincipaleDTO);
	}

}
