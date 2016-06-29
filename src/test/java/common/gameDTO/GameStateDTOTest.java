package common.gameDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneDTO;
import server.model.azioni.Exit;
import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import server.model.bonus.BonusTesseraAcquistata;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;

public class GameStateDTOTest {

	static GameState gameState;
	static GameStateDTO gameStateDTO;
	static Giocatore giocatore;
	static List<GiocatoreDTO> avversari;
	static GiocatoreDTO giocatoreDTO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatore = new Giocatore("Giocatore");
		giocatori.add(giocatore);
		Giocatore avversario=new Giocatore("Giocatore2");
		giocatori.add(avversario);
		gameState = new GameState();
		gameState.start(giocatori, "mappa1");
		giocatoreDTO=new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
		
		gameStateDTO=new GameStateDTO();
		gameStateDTO.inizializza(gameState);
		GiocatoreDTO avversarioDTO=new GiocatoreDTO();
		avversarioDTO.inizializza(avversario);
		avversari=new ArrayList<>();
		avversari.add(avversarioDTO);
	}

	@Test
	public void testInizializza() {
		gameStateDTO=new GameStateDTO();
		gameStateDTO.inizializza(gameState);
		
		assertTrue(gameStateDTO.getAvversari()!=null);
		assertTrue(gameStateDTO.getRegioni()!=null);
		assertTrue(gameStateDTO.getPedinaRE()!=null);
		assertTrue(gameStateDTO.getCittà()!=null);
		assertTrue(gameStateDTO.getConsiglieri()!=null);
		assertTrue(gameStateDTO.getOfferte()!=null);
		assertTrue(gameStateDTO.getRegioni()!=null);
		assertTrue(gameStateDTO.getPlanciaReDTO()!=null);
		assertTrue(gameStateDTO.getNomeMappa()!=null);
	}

	@Test
	public void testGetCittà() {
		List<CittàDTO> città=new ArrayList<>();
		for (Città c : gameState.getCittà()) {
			if (c instanceof CittàBonus) {
				CittàBonusDTO cittàBonusDTO = new CittàBonusDTO();
				cittàBonusDTO.inizializza((CittàBonus) c);
				città.add(cittàBonusDTO);
			} else {
				CittàDTO cittàDTO = new CittàDTO();
				cittàDTO.inizializza(c);
				città.add(cittàDTO);
			}
		}

		assertTrue(città.containsAll(gameStateDTO.getCittà()));
	}

	@Test(expected=NullPointerException.class)
	public void testSetCittà() {
		gameStateDTO.setCittà(null);
	}

	@Test
	public void testGetRegioni() {
		List<RegioneDTO> regioni = new ArrayList<>();
		for (Regione r : gameState.getRegioni()) {
			RegioneDTO regioneDTO = new RegioneDTO();
			regioneDTO.inizializza(r);
			regioni.add(regioneDTO);
		}
		gameStateDTO.setRegioni(regioni);
		
		assertTrue(regioni==gameStateDTO.getRegioni());
	}

	@Test(expected=NullPointerException.class)
	public void testSetRegioni() {
		gameStateDTO.setRegioni(null);
	}

	@Test
	public void testGetPedinaRE() {
		ReDTO pedinaRe = new ReDTO();
		pedinaRe.inizializza(gameState.getPedinaRe());
		gameStateDTO.setPedinaRE(pedinaRe);
		
		assertTrue(pedinaRe==gameStateDTO.getPedinaRE());
	}

	@Test(expected=NullPointerException.class)
	public void testSetPedinaRE() {
		gameStateDTO.setPedinaRE(null);
	}

	@Test
	public void testGetPlanciaReDTO() {
		PlanciaReDTO planciaReDTO = new PlanciaReDTO();
		planciaReDTO.inizializza(gameState.getPlanciaRe());
		gameStateDTO.setPlanciaReDTO(planciaReDTO);
		
		assertTrue(gameStateDTO.getPlanciaReDTO()==planciaReDTO);
	}

	@Test(expected=NullPointerException.class)
	public void testSetPlanciaReDTO() {
		gameStateDTO.setPlanciaReDTO(null);
	}

	@Test
	public void testGetConsiglieri() {
		List<ConsigliereDTO> consiglieri=new ArrayList<>();
		for (Consigliere c : gameState.getConsiglieri()) {
			ConsigliereDTO consigliereDTO = new ConsigliereDTO();
			consigliereDTO.inizializza(c);
			consiglieri.add(consigliereDTO);
		}
		gameStateDTO.setConsiglieri(consiglieri);
		
		assertTrue(consiglieri==gameStateDTO.getConsiglieri());
	}

	@Test(expected=NullPointerException.class)
	public void testSetConsiglieri() {
		gameStateDTO.setConsiglieri(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetAzioni() {
		gameStateDTO.setAzioni(null);
	}

	@Test
	public void testGetAzioniDisponibili() {
		List<AzioneDTO> azioniDisponibili=new ArrayList<>();
		AcquistoTesseraPermessoDTO acquisto=new AcquistoTesseraPermessoDTO();
		azioniDisponibili.add((AzioneDTO) acquisto);
		gameStateDTO.setAzioni(azioniDisponibili);
		
		assertTrue(azioniDisponibili==gameStateDTO.getAzioniDisponibili());
	}

	@Test
	public void testGetGiocatoreDTO() {
		giocatoreDTO.inizializza(giocatore);
		gameStateDTO.setGiocatoreDTO(giocatoreDTO);
		assertEquals(giocatoreDTO, gameStateDTO.getGiocatoreDTO());
	}

	@Test(expected=NullPointerException.class)
	public void testSetGiocatoreDTO() {
		gameStateDTO.setGiocatoreDTO(null);
	}

	@Test(expected=NullPointerException.class)
	public void testSetOfferte() {
		gameStateDTO.setOfferte(null);
	}

	@Test
	public void testGetOfferte() {
		List<OffertaDTO> offerte=new ArrayList<>();
		OffertaDTO offertaDTO=new OffertaDTO();
		offerte.add(offertaDTO);
		gameStateDTO.setOfferte(offerte);
		assertTrue(offerte==gameStateDTO.getOfferte());
	}

	@Test
	public void testGetAvversari() {
		gameStateDTO.setAvversari(avversari);
		assertTrue(gameStateDTO.getAvversari().contains(avversari.get(0)));
	}

	@Test(expected=NullPointerException.class)
	public void testSetAvversari() {
		gameStateDTO.setAvversari(null);
	}

	@Test
	public void testToString() {
		assertEquals("GameStateDTO [mappa=" + gameStateDTO.getCittà() + "\nregioni=" + gameStateDTO.getRegioni() + "\npedinaRE=" + gameStateDTO.getPedinaRE() + "\nplanciaReDTO="
				+ gameStateDTO.getPlanciaReDTO() + "\nconsiglieri=" + gameStateDTO.getConsiglieri() + "]", gameStateDTO.toString());
	}

	@Test
	public void testGetNomeMappa() {
		assertEquals("mappa1", gameStateDTO.getNomeMappa());
	}

}
