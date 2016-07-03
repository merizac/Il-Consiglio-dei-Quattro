package server.model.azioni.azioniBonus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.BonusTesseraPermessoNDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.TesseraPermesso;
import server.model.macchinaStati.StartEnd;
import server.model.notify.AvversarioNotify;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class BonusTesseraPermessoNTest {

	static GameState gameState;
	static Giocatore giocatore;
	static List<Notify> notify;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		notify = new ArrayList<>();
		giocatore = new Giocatore("Giocatore");
		giocatori.add(giocatore);
		giocatori.add(new Giocatore("B"));
		giocatori.add(new Giocatore("C"));
		gameState = new GameState();
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
		BonusTesseraPermessoN bonus=new BonusTesseraPermessoN();
		
		TesseraPermesso tessera=gameState.getRegioni().get(0).getTesserePermessoScoperte().get(0);
		bonus.setRegione(gameState.getRegioni().get(0));
		bonus.setTesseraScoperta(tessera);
		giocatore.getBonusNobiltà().add(bonus);
		notify.clear();
		bonus.eseguiAzione(gameState);
		
		System.out.println(notify);
		assertEquals(2, gameState.getRegioni().get(0).getTesserePermessoScoperte().size());
		assertTrue(!giocatore.getBonusNobiltà().contains(bonus));
		int size=notify.size();
		assertTrue(notify.get(size-4) instanceof GameStateNotify);
		assertTrue(notify.get(size-3) instanceof AvversarioNotify);
		assertTrue(notify.get(size-2) instanceof GiocatoreNotify);
		assertTrue(notify.get(size-1) instanceof MessageNotify);
		assertTrue(gameState.getStato() instanceof StartEnd);
	}

	@Test
	public void testGetAzioneDTO() {
		assertTrue(new BonusTesseraPermessoN().getAzioneDTO() instanceof BonusTesseraPermessoNDTO);
	}

	@Test
	public void testGetRegione() {
		BonusTesseraPermessoN bonus=new BonusTesseraPermessoN();
		bonus.setRegione(gameState.getRegioni().get(0));
		assertTrue(gameState.getRegioni().get(0)==bonus.getRegione());
	}

	@Test(expected=NullPointerException.class)
	public void testSetRegione() {
		BonusTesseraPermessoN bonus=new BonusTesseraPermessoN();
		bonus.setRegione(null);
	}

	@Test
	public void testGetTesseraPermesso() {
		BonusTesseraPermessoN bonus=new BonusTesseraPermessoN();
		TesseraPermesso tessera=gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(0);
		bonus.setTesseraScoperta(tessera);
		assertTrue(tessera==bonus.getTesseraScoperta());
	}

	@Test(expected=NullPointerException.class)
	public void testSetTesseraPermesso() {
		BonusTesseraPermessoN bonus=new BonusTesseraPermessoN();
		bonus.setTesseraScoperta(null);
	}
	
}