package server.model.bonus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.gameDTO.CittàBonusDTO;
import server.model.azioni.azioniBonus.BonusGettoneN;
import server.model.game.CittàBonus;
import server.model.game.Emporio;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class BonusGettoneRicompensaTest {

	static GameState gameState;
	static ArrayList<CittàBonus> cittàGiocatore;
	static CittàBonusDTO cittàDTO;
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
		cittàGiocatore=new ArrayList<>();
		cittàGiocatore.add((CittàBonus)gameState.getRegioni().get(0).getCittàRegione().get(0));
		cittàGiocatore.add((CittàBonus)gameState.getRegioni().get(1).getCittàRegione().get(0));
		cittàGiocatore.add((CittàBonus)gameState.getRegioni().get(2).getCittàRegione().get(0));
		cittàDTO=new CittàBonusDTO();
		cittàDTO.inizializza((CittàBonus)gameState.getRegioni().get(0).getCittàRegione().get(0));
	}

	@Test
	public void testUsaBonus() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa(1);
		bonus.setCittàGiocatore(cittàGiocatore);
		bonus.setCittàDTO(cittàDTO);
		
		for(CittàBonus c: cittàGiocatore){
			c.aggiungiEmporio(new Emporio(gameState.getGiocatoreCorrente().getColoreGiocatore()));
		}
	
		
		bonus.usaBonus(gameState);
		
		assertTrue(gameState.getGiocatoreCorrente().getBonusNobiltà().get(0).equals(bonus.getAzioneBonus()));
		for(CittàBonus c: cittàGiocatore){
			c.getEmpori().clear();
		}
	}
	
	@Test
	public void testUsaBonusNotifica() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa(1);
		bonus.setCittàGiocatore(new ArrayList<>());
		bonus.setCittàDTO(cittàDTO);
		
		notify.clear();
		bonus.usaBonus(gameState);
		
		assertTrue(notify.size()==1);
		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test
	public void testBonusGettoneRicompensa() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa(2);
		assertEquals(2, bonus.getNumeroGettoni());
	}

	@Test
	public void testGetAzioneBonus() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa();
		bonus.setNumeroGettoni(2);
		BonusGettoneN bonusN=new BonusGettoneN();
		bonusN.setNumeroGettoni(2);
		assertEquals(bonusN,bonus.getAzioneBonus());
	}

	@Test
	public void testGetNumeroGettoni() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa();
		bonus.setNumeroGettoni(2);
		assertEquals(2, bonus.getNumeroGettoni());
		bonus.setNumeroGettoni(1);
		assertEquals(1, bonus.getNumeroGettoni());
	}

	@Test
	public void testGetCittàGiocatore() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa();
		bonus.setCittàGiocatore(cittàGiocatore);
		assertTrue(cittàGiocatore==bonus.getCittàGiocatore());
	}

	@Test(expected=NullPointerException.class)
	public void testSetCittàGiocatore() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa();
		bonus.setCittàGiocatore(null);
	}

	@Test
	public void testGetCittàDTO() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa();
		bonus.setCittàDTO(cittàDTO);
		assertTrue(cittàDTO==bonus.getCittàDTO());
	}

	@Test(expected=NullPointerException.class)
	public void testSetCittàDTO() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa();
		bonus.setCittàDTO(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetNumeroGettoniNegativo() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa();
		bonus.setNumeroGettoni(3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNumeroGettoniMaggiore2() {
		BonusGettoneRicompensa bonus=new BonusGettoneRicompensa();
		bonus.setNumeroGettoni(-1);
	}

}
