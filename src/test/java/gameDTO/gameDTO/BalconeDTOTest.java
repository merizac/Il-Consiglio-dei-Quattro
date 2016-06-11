package gameDTO.gameDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Balcone;
import game.Colore;
import game.Consigliere;
import game.GameState;
import game.Giocatore;

public class BalconeDTOTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
	}

	@Test
	public void testGetConsiglieri(){
		BalconeDTO balconeDTO=new BalconeDTO();
		ArrayList<ConsigliereDTO> consiglieriDTO=new ArrayList<>();
		ConsigliereDTO c1=new ConsigliereDTO();
		Consigliere cons1=new Consigliere(new Colore("A"));
		c1.inizializza(cons1);
		ConsigliereDTO c2=new ConsigliereDTO();
		Consigliere cons2=new Consigliere(new Colore("B"));
		c2.inizializza(cons2);
		Consigliere cons3=new Consigliere(new Colore("C"));
		ConsigliereDTO c3=new ConsigliereDTO();
		c3.inizializza(cons3);
		Consigliere cons4=new Consigliere(new Colore("D"));
		ConsigliereDTO c4=new ConsigliereDTO();
		c4.inizializza(cons4);
		consiglieriDTO.add(c1);
		consiglieriDTO.add(c2);
		consiglieriDTO.add(c3);
		consiglieriDTO.add(c4);
		
		balconeDTO.setConsiglieri(consiglieriDTO);
		assertTrue(balconeDTO.getConsiglieri().contains(c1));
		assertTrue(balconeDTO.getConsiglieri().contains(c2));
		assertTrue(balconeDTO.getConsiglieri().contains(c3));
		assertTrue(balconeDTO.getConsiglieri().contains(c4));
		
		assertTrue(balconeDTO.getConsiglieri()==consiglieriDTO);
	}

	@Test
	public void testSetConsiglieri() {
		BalconeDTO balconeDTO=new BalconeDTO();
		ArrayList<ConsigliereDTO> consiglieriDTO=new ArrayList<>();
		ConsigliereDTO c1=new ConsigliereDTO();
		Consigliere cons1=new Consigliere(new Colore("A"));
		c1.inizializza(cons1);
		ConsigliereDTO c2=new ConsigliereDTO();
		Consigliere cons2=new Consigliere(new Colore("B"));
		c2.inizializza(cons2);
		Consigliere cons3=new Consigliere(new Colore("C"));
		ConsigliereDTO c3=new ConsigliereDTO();
		c3.inizializza(cons3);
		Consigliere cons4=new Consigliere(new Colore("D"));
		ConsigliereDTO c4=new ConsigliereDTO();
		c4.inizializza(cons4);
		consiglieriDTO.add(c1);
		consiglieriDTO.add(c2);
		consiglieriDTO.add(c3);
		consiglieriDTO.add(c4);
		
		balconeDTO.setConsiglieri(consiglieriDTO);
		assertTrue(balconeDTO.getConsiglieri().contains(c1));
		assertTrue(balconeDTO.getConsiglieri().contains(c2));
		assertTrue(balconeDTO.getConsiglieri().contains(c3));
		assertTrue(balconeDTO.getConsiglieri().contains(c4));
		
		assertTrue(balconeDTO.getConsiglieri()==consiglieriDTO);
	}

	@Test
	public void testInizializza() {
		Balcone balcone=gameState.getPlanciaRe().getBalconeRe();
		BalconeDTO balconeDTO=new BalconeDTO();
		
		balconeDTO.inizializza(balcone);
		
		assertTrue(balconeDTO.getConsiglieri().get(0).getColoreConsigliere()==balcone.getConsigliere().element().getColore().getColore());
	}

}
