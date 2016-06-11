package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bonus.Bonus;
import bonus.BonusAiutanti;
import game.Balcone;
import game.CartaPolitica;
import game.Città;
import game.CittàBonus;
import game.Colore;
import game.Consigliere;
import game.GameState;
import game.Giocatore;
import game.Regione;
import game.TesseraPermesso;
import gameDTO.gameDTO.BalconeDTO;
import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.CittàBonusDTO;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ColoreDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class ControlloParametriTest {

	static GameState gameState;
	ControlloParametri controlloParametri;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
	}
	
	@Before
	public void berfortest(){
		controlloParametri=new ControlloParametri();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCercaRegioneConRegioneNonEsistente() {
		RegioneDTO regioneDTO=new RegioneDTO();
		
		BalconeDTO balconeDTO=new BalconeDTO();
		balconeDTO.inizializza(gameState.getRegioni().get(1).getBalcone());;
		
		regioneDTO.setBalcone(balconeDTO);
		regioneDTO.setBonusRegione(gameState.getRegioni().get(0).getBonusRegione());
		regioneDTO.setNome("A");
		
		controlloParametri.cercaRegione(regioneDTO, gameState.getRegioni());
	}
	
	@Test
	public void testCercaRegione() {
		Regione regione=gameState.getRegioni().get(0);
		RegioneDTO regioneDTO=new RegioneDTO();
		regioneDTO.inizializza(regione);
		
		Regione r=controlloParametri.cercaRegione(regioneDTO, gameState.getRegioni());
		
		assertTrue(r==regione);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCercaBalconeConBalconeNonEsistente() {
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
	public void testCercaBalcone() {
		Balcone balcone=gameState.getRegioni().get(0).getBalcone();
		BalconeDTO balconeDTO=new BalconeDTO();
		balconeDTO.inizializza(balcone);
		
		Balcone b=controlloParametri.cercaBalcone(balconeDTO, gameState.getPlanciaRe().getBalconeRe(), gameState.getRegioni());
		
		assertTrue(b==balcone);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCercaCartePoliticaDiverse() {
		ArrayList<CartaPolitica> cartePolitica=new ArrayList<>();
		ArrayList<CartaPoliticaDTO> cartePoliticaDTO=new ArrayList<>();
		
		CartaPoliticaDTO cartaDTO=new CartaPoliticaDTO();
		cartaDTO.inizializza(new CartaPolitica(new Colore("A")));
		
		cartePolitica.add(gameState.getMazzoCartePolitica().getCarte().get(0));
		cartePoliticaDTO.add(cartaDTO);
		
		controlloParametri.cercaCartePolitica(cartePoliticaDTO, cartePolitica);
	}
	
	@Test
	public void testCercaCartePolitica() {
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

	@Test(expected=IllegalArgumentException.class)
	public void testCercaCittàNonEsistente() {
		CittàDTO cittàDTO=new CittàDTO();
		ColoreDTO coloreDTO=new ColoreDTO();
		coloreDTO.inizializza(new Colore("C"));
		cittàDTO.setColoreDTO(coloreDTO);
		cittàDTO.setNome("A");
		
		controlloParametri.cercaCittà(cittàDTO, gameState.getCittà());
	}
	
	@Test
	public void testCercaCittà() {
		Città città=gameState.getRegioni().get(0).getCittàRegione().get(2);
		CittàDTO cittàDTO=new CittàDTO();
		cittàDTO.inizializza(città);
		
		Città c=controlloParametri.cercaCittà(cittàDTO, gameState.getCittà());
		
		assertTrue(città==c);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCercaCittàBonusNonEsistente() {
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
	public void testCercaCittàBonus() {
		CittàBonus città=(CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(2);
		CittàBonusDTO cittàDTO=new CittàBonusDTO();
		cittàDTO.inizializza(città);
		
		CittàBonus c=(CittàBonus) ControlloParametri.cercaCittà(cittàDTO, gameState.getCittà());
		
		assertTrue(città==c);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCercaTesseraPermessoNonEsistente() {
		TesseraPermessoDTO tesseraPermessoDTO=new TesseraPermessoDTO();
		tesseraPermessoDTO.inizializza(gameState.getRegioni().get(1).getTesserePermessoScoperte().get(0));
		
		controlloParametri.cercaTesseraPermesso(tesseraPermessoDTO, gameState.getRegioni().get(0).getTesserePermessoScoperte());
	}
	
	@Test
	public void testCercaTesseraPermesso() {
		TesseraPermesso tesseraPermesso=gameState.getRegioni().get(0).getTesserePermessoScoperte().get(0);
		TesseraPermessoDTO tesseraPermessoDTO=new TesseraPermessoDTO();
		tesseraPermessoDTO.inizializza(tesseraPermesso);

		controlloParametri.cercaTesseraPermesso(tesseraPermessoDTO, gameState.getRegioni().get(0).getTesserePermessoScoperte());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCercaConsigliereNonEsistente() {
		Consigliere consigliere=new Consigliere(new Colore("A"));
		ConsigliereDTO consigliereDTO=new ConsigliereDTO();
		consigliereDTO.inizializza(consigliere);
		
		controlloParametri.cercaConsigliere(consigliereDTO, gameState.getConsiglieri());
	}

	@Test
	public void testCercaConsigliere() {
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
