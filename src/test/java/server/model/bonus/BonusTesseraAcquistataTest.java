package server.model.bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.traversal.NodeFilter;

import server.model.azioni.azioniBonus.BonusTesseraAcquistataN;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class BonusTesseraAcquistataTest {

	static GameState gameState;
	static List<Notify> notify;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testUsaBonusSenzaTessere() {
		BonusTesseraAcquistata bonus=new BonusTesseraAcquistata();
		gameState.getGiocatoreCorrente().getTesserePermesso().clear();
		
		notify.clear();
		bonus.usaBonus(gameState);
		
		assertEquals(1, notify.size());
		assertTrue(notify.get(0) instanceof MessageNotify);
	}
	
	@Test
	public void testUsaBonusConTessere() {
		BonusTesseraAcquistata bonus=new BonusTesseraAcquistata();
		gameState.getGiocatoreCorrente().aggiungiTesseraPermesso(gameState.getRegioni().get(0).getTesserePermessoScoperte().get(0));
		
		notify.clear();
		bonus.usaBonus(gameState);

		assertTrue(gameState.getGiocatoreCorrente().getBonusNobiltà().get(0).equals(bonus.getAzioneBonus()));
		assertEquals(1, gameState.getGiocatoreCorrente().getBonusNobiltà().size());
	}

	@Test
	public void testGetAzioneBonus() {
		BonusTesseraAcquistata bonus=new BonusTesseraAcquistata();
		BonusTesseraAcquistataN bonusN=new BonusTesseraAcquistataN();
		bonusN.setId(1);
		assertEquals(bonusN, bonus.getAzioneBonus());
	}

}
