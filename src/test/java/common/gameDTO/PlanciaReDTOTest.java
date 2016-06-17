package common.gameDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.gameDTO.BalconeDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.PlanciaReDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class PlanciaReDTOTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);

	}

	@Test
	public void testGetBalconeRe() {
		PlanciaReDTO planciaDTO=new PlanciaReDTO();
		ArrayList<ConsigliereDTO> balconeRe = new ArrayList<>();
		BalconeDTO balcone=new BalconeDTO();
		balcone.inizializza(gameState.getPlanciaRe().getBalconeRe());

		planciaDTO.setBalconeRe(balcone);
		
		assertTrue(balcone==planciaDTO.getBalconeRe());
	}

	@Test
	public void testSetBalconeRe() {
		PlanciaReDTO planciaDTO=new PlanciaReDTO();
		ArrayList<ConsigliereDTO> balconeRe = new ArrayList<>();
		BalconeDTO balcone=new BalconeDTO();
		balcone.inizializza(gameState.getPlanciaRe().getBalconeRe());

		planciaDTO.setBalconeRe(balcone);
	
		
		assertTrue(balcone==planciaDTO.getBalconeRe());
	}

	@Test
	public void testGetBonusPremioRe() {
		PlanciaReDTO planciaDTO=new PlanciaReDTO();
		ArrayList<Bonus> bonus=new ArrayList<>();
		bonus.add(new BonusPuntiVittoria(2));
		planciaDTO.setBonusPremioRe(bonus);
		
		assertTrue(bonus==planciaDTO.getBonusPremioRe());
	}

	@Test
	public void testSetBonusPremioRe() {
		PlanciaReDTO planciaDTO=new PlanciaReDTO();
		ArrayList<Bonus> bonus=new ArrayList<>();
		bonus.add(new BonusPuntiVittoria(2));
		planciaDTO.setBonusPremioRe(bonus);
		
		assertTrue(bonus==planciaDTO.getBonusPremioRe());
	}

	@Test
	public void testInizializza() {
		PlanciaReDTO planciaDTO=new PlanciaReDTO();
		planciaDTO.inizializza(gameState.getPlanciaRe());
		
		assertTrue(planciaDTO.getBalconeRe().getConsiglieri().get(0) instanceof ConsigliereDTO);
		assertTrue(gameState.getPlanciaRe().getBonusPremioRe()==planciaDTO.getBonusPremioRe());
	}

}
