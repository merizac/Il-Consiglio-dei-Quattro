package server.model.azioni.azioniBonus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.BonusTesseraAcquistataNDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.TesseraPermesso;
import server.model.macchinaStati.StartEnd;
import server.model.notify.AvversarioNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.Notify;
import utility.Observer;

public class BonusTesseraAcquistataNTest {

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
		BonusTesseraAcquistataN bonus=new BonusTesseraAcquistataN();
		TesseraPermesso tessera=gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(0);
		bonus.setTesseraPermesso(tessera);
		giocatore.getBonusNobiltà().add(bonus);
		
		notify.clear();
		bonus.eseguiAzione(gameState);
		
		assertTrue(notify.get(notify.size() - 3) instanceof AvversarioNotify);
		assertTrue(notify.get(notify.size() - 2) instanceof GiocatoreNotify);
		assertTrue(!giocatore.getBonusNobiltà().contains(bonus));
		assertTrue(gameState.getStato() instanceof StartEnd);
	}

	@Test
	public void testGetAzioneDTO() {
		assertTrue(new BonusTesseraAcquistataN().getAzioneDTO() instanceof BonusTesseraAcquistataNDTO);
	}
	
	@Test
	public void testGetTesseraPermesso() {
		BonusTesseraAcquistataN bonus=new BonusTesseraAcquistataN();
		TesseraPermesso tessera=gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(0);
		bonus.setTesseraPermesso(tessera);
		assertTrue(tessera==bonus.getTesseraPermesso());
	}

	@Test(expected=NullPointerException.class)
	public void testSetTesseraPermesso() {
		BonusTesseraAcquistataN bonus=new BonusTesseraAcquistataN();
		bonus.setTesseraPermesso(null);
	}

	@Test
	public void testGetId1() {
		BonusTesseraAcquistataN bonus=new BonusTesseraAcquistataN();
		bonus.setId(1);
		assertEquals(1, bonus.getId());
	}
	
	@Test
	public void testGetId2() {
		BonusTesseraAcquistataN bonus=new BonusTesseraAcquistataN();
		bonus.setId(2);
		assertEquals(2, bonus.getId());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetId0() {
		BonusTesseraAcquistataN bonus=new BonusTesseraAcquistataN();
		bonus.setId(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetId3() {
		BonusTesseraAcquistataN bonus=new BonusTesseraAcquistataN();
		bonus.setId(3);
	}

}
