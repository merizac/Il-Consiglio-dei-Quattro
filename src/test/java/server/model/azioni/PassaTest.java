package server.model.azioni;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.PassaDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.macchinaStati.Stato10;
import server.model.macchinaStati.StatoAcquistoMarket;
import server.model.notify.Notify;
import utility.Observer;

public class PassaTest {

	static GameState gameState;
	static Giocatore giocatore;
	static List<Notify> notify;
	static Giocatore giocatoreC;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		notify = new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		giocatoreC=new Giocatore("C");
		giocatori.add(new Giocatore("B"));
		giocatori.add(giocatoreC);
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
		gameState.setStato(new StatoAcquistoMarket(gameState));
		Passa passa=new Passa();
		passa.eseguiAzione(gameState);
		assertTrue(gameState.getStato() instanceof StatoAcquistoMarket);
	}

	@Test
	public void testIsTurnoTrue() {
		StatoAcquistoMarket acquisto=new StatoAcquistoMarket(gameState);
		gameState.setStato(acquisto);
		gameState.setGiocatoreCorrente(giocatore);
		Passa passa=new Passa();
		assertTrue(passa.isTurno(acquisto.getGiocatori().get(0), gameState));
	}
	
	@Test
	public void testIsTurnoFalse() {
		Stato10 acquisto=new Stato10(gameState);
		gameState.setStato(acquisto);
		gameState.setGiocatoreCorrente(giocatore);
		Passa passa=new Passa();
		assertTrue(passa.isTurno(giocatore,gameState));
	}

	@Test
	public void testGetAzioneDTO() {
		assertTrue(new Passa().getAzioneDTO() instanceof PassaDTO);
	}

}
