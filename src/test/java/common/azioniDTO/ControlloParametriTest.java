package common.azioniDTO;

import static org.junit.Assert.*;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.ControlloParametri;
import common.gameDTO.BalconeDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàBonusDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ColoreDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.game.Balcone;
import server.model.game.CartaPolitica;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Colore;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import utility.ParameterException;

public class ControlloParametriTest {

	static GameState gameState;
	ControlloParametri controlloParametri;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");	}
	
	@Before
	public void berfortest(){
		controlloParametri=new ControlloParametri();
	}

	@Test(expected=ParameterException.class)
	public void testCercaRegioneConRegioneNonEsistente() throws ParameterException {
		RegioneDTO regioneDTO=new RegioneDTO();
		
		BalconeDTO balconeDTO=new BalconeDTO();
		balconeDTO.inizializza(gameState.getRegioni().get(1).getBalcone());;
		
		regioneDTO.setBalcone(balconeDTO);
		regioneDTO.setBonusRegione(gameState.getRegioni().get(0).getBonusRegione());
		regioneDTO.setNome("A");
		
		controlloParametri.cercaRegione(regioneDTO, gameState.getRegioni());
	}
	
	@Test
	public void testCercaRegione() throws ParameterException {
		Regione regione=gameState.getRegioni().get(0);
		RegioneDTO regioneDTO=new RegioneDTO();
		regioneDTO.inizializza(regione);
		
		Regione r=controlloParametri.cercaRegione(regioneDTO, gameState.getRegioni());
		
		assertTrue(r==regione);
	}

	@Test(expected=ParameterException.class)
	public void testCercaBalconeConBalconeNonEsistente() throws ParameterException {
		BalconeDTO balconeDTO=new BalconeDTO();
		ArrayList<ConsigliereDTO> consiglieriDTO=new ArrayList();
		ConsigliereDTO consigliere=new ConsigliereDTO();
		consigliere.setColoreConsigliere("A");
		consiglieriDTO.add(consigliere);
		consiglieriDTO.add(consigliere);
		consiglieriDTO.add(consigliere);
		consiglieriDTO.add(consigliere);
		balconeDTO.setConsiglieri(consiglieriDTO);
		
		controlloParametri.cercaBalcone(balconeDTO,  gameState.getPlanciaRe().getBalconeRe(), gameState.getRegioni());
	}

	@Test
	public void testCercaBalcone() throws ParameterException {
		Balcone balcone=gameState.getRegioni().get(0).getBalcone();
		BalconeDTO balconeDTO=new BalconeDTO();
		balconeDTO.inizializza(balcone);
		
		Balcone b=controlloParametri.cercaBalcone(balconeDTO, gameState.getPlanciaRe().getBalconeRe(), gameState.getRegioni());
		
		assertTrue(b==balcone);
	}

	@Test(expected=ParameterException.class)
	public void testCercaCartePoliticaDiverse() throws ParameterException {
		ArrayList<CartaPolitica> cartePolitica=new ArrayList<>();
		ArrayList<CartaPoliticaDTO> cartePoliticaDTO=new ArrayList<>();
		
		CartaPoliticaDTO cartaDTO=new CartaPoliticaDTO();
		cartaDTO.inizializza(new CartaPolitica(new Colore("A")));
		
		cartePolitica.add(gameState.getMazzoCartePolitica().getCarte().get(0));
		cartePoliticaDTO.add(cartaDTO);
		
		controlloParametri.cercaCartePolitica(cartePoliticaDTO, cartePolitica);
	}
	
	@Test
	public void testCercaCartePolitica() throws ParameterException {
		ArrayList<CartaPolitica> cartePolitica=new ArrayList<>();
		ArrayList<CartaPoliticaDTO> cartePoliticaDTO=new ArrayList<>();
		
		CartaPoliticaDTO cartaDTO1=new CartaPoliticaDTO();
		CartaPoliticaDTO cartaDTO2=new CartaPoliticaDTO();
		CartaPoliticaDTO cartaDTO3=new CartaPoliticaDTO();
		cartaDTO1.inizializza(gameState.getMazzoCartePolitica().getCarte().get(0));
		cartePoliticaDTO.add(cartaDTO1);
		cartaDTO2.inizializza(gameState.getMazzoCartePolitica().getCarte().get(1));
		cartePoliticaDTO.add(cartaDTO2);
		cartaDTO3.inizializza(gameState.getMazzoCartePolitica().getCarte().get(2));
		cartePoliticaDTO.add(cartaDTO3);
		
		cartePolitica.add(gameState.getMazzoCartePolitica().getCarte().get(0));
		cartePolitica.add(gameState.getMazzoCartePolitica().getCarte().get(1));
		cartePolitica.add(gameState.getMazzoCartePolitica().getCarte().get(2));
		
		ArrayList<CartaPolitica> carte=(ArrayList<CartaPolitica>) controlloParametri.cercaCartePolitica(cartePoliticaDTO, cartePolitica);
		ArrayList<CartaPolitica> vuoto=new ArrayList<>();
		
		assertEquals(vuoto, cartePolitica);
	}

	@Test(expected=ParameterException.class)
	public void testCercaCittàNonEsistente() throws ParameterException {
		CittàDTO cittàDTO=new CittàDTO();
		ColoreDTO coloreDTO=new ColoreDTO();
		coloreDTO.inizializza(new Colore("C"));
		cittàDTO.setColoreDTO(coloreDTO);
		cittàDTO.setNome("A");
		
		controlloParametri.cercaCittà(cittàDTO, gameState.getCittà());
	}
	
	@Test
	public void testCercaCittà() throws ParameterException {
		Città città=gameState.getRegioni().get(0).getCittàRegione().get(2);
		CittàDTO cittàDTO=new CittàDTO();
		cittàDTO.inizializza(città);
		
		Città c=controlloParametri.cercaCittà(cittàDTO, gameState.getCittà());
		
		assertTrue(città==c);
	}

	@Test(expected=ParameterException.class)
	public void testCercaCittàBonusNonEsistente() throws ParameterException {
		CittàBonusDTO cittàBonusDTO=new CittàBonusDTO();
		ColoreDTO coloreDTO=new ColoreDTO();
		coloreDTO.inizializza(new Colore("C"));
		cittàBonusDTO.setColoreDTO(coloreDTO);
		cittàBonusDTO.setNome("A");
		ArrayList<Bonus> bonus=new ArrayList<>();
		bonus.add(new BonusAiutanti(1));
		cittàBonusDTO.setBonus(bonus);
		
		controlloParametri.cercaCittà(cittàBonusDTO, gameState.getCittà());
	}
	
	@Test
	public void testCercaCittàBonus() throws ParameterException {
		CittàBonus città=(CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(2);
		CittàBonusDTO cittàDTO=new CittàBonusDTO();
		cittàDTO.inizializza(città);
		
		CittàBonus c=(CittàBonus) ControlloParametri.cercaCittà(cittàDTO, gameState.getCittà());
		
		assertTrue(città==c);
	}

	@Test(expected=ParameterException.class)
	public void testCercaTesseraPermessoNonEsistente() throws ParameterException {
		TesseraPermessoDTO tesseraPermessoDTO=new TesseraPermessoDTO();
		tesseraPermessoDTO.inizializza(gameState.getRegioni().get(1).getTesserePermessoScoperte().get(0));
		
		controlloParametri.cercaTesseraPermesso(tesseraPermessoDTO, gameState.getRegioni().get(0).getTesserePermessoScoperte());
	}
	
	@Test
	public void testCercaTesseraPermesso() throws ParameterException {
		TesseraPermesso tesseraPermesso=gameState.getRegioni().get(0).getTesserePermessoScoperte().get(0);
		TesseraPermessoDTO tesseraPermessoDTO=new TesseraPermessoDTO();
		tesseraPermessoDTO.inizializza(tesseraPermesso);

		controlloParametri.cercaTesseraPermesso(tesseraPermessoDTO, gameState.getRegioni().get(0).getTesserePermessoScoperte());
	}

	@Test(expected=ParameterException.class)
	public void testCercaConsigliereNonEsistente() throws ParameterException {
		Consigliere consigliere=new Consigliere(new Colore("A"));
		ConsigliereDTO consigliereDTO=new ConsigliereDTO();
		consigliereDTO.inizializza(consigliere);
		
		controlloParametri.cercaConsigliere(consigliereDTO, gameState.getConsiglieri());
	}

	@Test
	public void testCercaConsigliere() throws ParameterException {
		Consigliere consigliere=gameState.getConsiglieri().get(0);
		ConsigliereDTO consigliereDTO=new ConsigliereDTO();
		consigliereDTO.inizializza(consigliere);
		
		Consigliere c=controlloParametri.cercaConsigliere(consigliereDTO, gameState.getConsiglieri());

		assertEquals(consigliere, c);
	}

	/*	@Test
	public void testCercaOfferta() {
		fail("Not yet implemented");
	}

	@Test
	public void testCercaGiocatore() {
		fail("Not yet implemented");
	}
*/
}
