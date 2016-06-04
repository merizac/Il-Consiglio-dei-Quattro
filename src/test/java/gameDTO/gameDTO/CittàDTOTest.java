package gameDTO.gameDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Città;
import game.Colore;
import game.Emporio;
import game.GameState;
import game.Giocatore;

public class CittàDTOTest {

	static GameState gameState;
	static Città città;
	static CittàDTO cittàDTO;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		città=gameState.getRegioni().get(0).getCittàRegione().get(0);
		città.aggiungiEmporio(new Emporio(new Colore("A")));
		città.aggiungiEmporio(new Emporio(new Colore("B")));
		
		cittàDTO=new CittàDTO();
	}

	@Test
	public void testGetNome() {
		CittàDTO c=new CittàDTO();
		c.setNome("A");
		
		assertEquals("A", c.getNome());
	}

	@Test
	public void testSetNome() {
		CittàDTO c=new CittàDTO();
		c.setNome("A");
		
		assertEquals("A", c.getNome());
	}

	@Test
	public void testGetColoreDTO() {
		CittàDTO c=new CittàDTO();
		ColoreDTO coloreDTO=new ColoreDTO();
		coloreDTO.inizializza(new Colore("A"));
		c.setColoreDTO(coloreDTO);

		assertEquals("A", c.getColoreDTO().getColore());
	}

	@Test
	public void testSetColoreDTO() {
		CittàDTO c=new CittàDTO();
		ColoreDTO coloreDTO=new ColoreDTO();
		coloreDTO.inizializza(new Colore("A"));
		c.setColoreDTO(coloreDTO);

		assertEquals("A", c.getColoreDTO().getColore());
	}

	@Test
	public void testGetEmpori() {
		CittàDTO c=new CittàDTO();
		HashSet<String> empori=new HashSet<>();
		empori.add("A");
		c.setEmpori(empori);

		assertTrue(c.getEmpori()==empori);
	}

	@Test
	public void testSetEmpori() {
		CittàDTO c=new CittàDTO();
		HashSet<String> empori=new HashSet<>();
		empori.add("A");
		c.setEmpori(empori);

		assertTrue(c.getEmpori()==empori);
	}

	@Test
	public void testInizializza() {
		cittàDTO.inizializza(città);
		assertEquals(città.getNome(), cittàDTO.getNome());
		assertEquals(città.getColoreCittà().getColore(), cittàDTO.getColoreDTO().getColore());
		assertEquals(città.getEmpori().size(), cittàDTO.getEmpori().size());
	}

}
