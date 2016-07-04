package server.model.azioni.azioniBonus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.BonusGettoneNDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.CittàBonus;
import server.model.game.ColoreCittà;
import server.model.game.Emporio;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.macchinaStati.StartEnd;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class BonusGettoneNTest {

	static GameState gameState;
	static Giocatore giocatore;
	static List<Notify> notify;
	static List<CittàBonus> città;

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
		città = new ArrayList<>();
		città.add((CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(0));
		città.add((CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(1));
	}

	@Test
	public void dueGettoniConGliStessiBonus() {
		List<CittàBonus> cittàStessoGettone=new ArrayList<CittàBonus>();
		List<Bonus> bonusCittà=new ArrayList<>();
		List<Bonus> bonusDaRimettere=new ArrayList<>();
		
		ColoreCittà colore=new ColoreCittà("A", new BonusPuntiVittoria(2));
		CittàBonus città1=new CittàBonus("A", gameState.getRegioni().get(0), colore, bonusCittà);
		CittàBonus città2=new CittàBonus("B", gameState.getRegioni().get(0), colore, bonusCittà);
		
		cittàStessoGettone.add(città1);
		cittàStessoGettone.add(città2);
	
		for(CittàBonus c:cittàStessoGettone){
			c.aggiungiEmporio(new Emporio(giocatore.getColoreGiocatore()));
		}

		BonusGettoneN bonus= new BonusGettoneN();
		bonus.setNumeroGettoni(2);
		bonus.setCittà(cittàStessoGettone);

		notify.clear();
		bonus.eseguiAzione(gameState);
		
		cittàStessoGettone.get(1).getBonus().clear();
		cittàStessoGettone.get(1).getBonus().addAll(bonusDaRimettere);
		
		assertEquals(1, notify.size());
		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test
	public void giocatoreNonHaEmporiNellaCittà() {
		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setNumeroGettoni(2);
		bonus.setCittà(città);

		for (CittàBonus c : città) {
			c.getEmpori().clear();
		}

		notify.clear();
		bonus.eseguiAzione(gameState);

		assertEquals(1, notify.size());
		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test
	public void buonFine() {
		List<CittàBonus> cittàStessoGettone = new ArrayList<CittàBonus>();

		cittàStessoGettone.add((CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(0));
		cittàStessoGettone.add((CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(1));

		for (CittàBonus c : cittàStessoGettone) {
			c.aggiungiEmporio(new Emporio(gameState.getGiocatoreCorrente().getColoreGiocatore()));
		}

		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setNumeroGettoni(2);
		bonus.setCittà(città);

		gameState.getGiocatoreCorrente().getBonusNobiltà().add(bonus);

		notify.clear();
		bonus.eseguiAzione(gameState);

		assertTrue(gameState.getStato() instanceof StartEnd);
		assertTrue(!giocatore.getBonusNobiltà().contains(bonus));

	}

	@Test
	public void testGetAzioneDTO() {
		BonusGettoneNDTO bonusDTO = new BonusGettoneNDTO();
		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setNumeroGettoni(1);
		bonusDTO.setNumeroGettoni(1);

		assertEquals(bonusDTO.toString(), bonus.getAzioneDTO().toString());
	}

	@Test
	public void testGetCittà() {
		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setCittà(città);

		assertTrue(città == bonus.getCittà());
	}

	@Test(expected = NullPointerException.class)
	public void testSetCittà() {
		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setCittà(null);
	}

	@Test
	public void testGetNumeroGettoni1() {
		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setNumeroGettoni(1);
		assertEquals(1, bonus.getNumeroGettoni());
	}

	@Test
	public void testGetNumeroGettoni2() {
		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setNumeroGettoni(2);
		assertEquals(2, bonus.getNumeroGettoni());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumeroGettoni0() {
		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setNumeroGettoni(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumeroGettoni3() {
		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setNumeroGettoni(3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumeroGettoniNegativo() {
		BonusGettoneN bonus = new BonusGettoneN();
		bonus.setNumeroGettoni(-1);
	}
}