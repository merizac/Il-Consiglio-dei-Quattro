package gameDTO.gameDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import bonus.Bonus;
import bonus.BonusAiutanti;
import bonus.BonusPuntiVittoria;
import game.Consigliere;
import game.GameState;
import game.Giocatore;
import game.TesseraPermesso;

public class RegioneDTOTest {

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
	public void testGetNome() {
		RegioneDTO regioneDTO=new RegioneDTO();
		
		regioneDTO.setNome("A");
		assertTrue("A"==regioneDTO.getNome());

	}

	@Test
	public void testSetNome() {
		RegioneDTO regioneDTO=new RegioneDTO();
		
		regioneDTO.setNome("A");
		assertTrue("A"==regioneDTO.getNome());
	}

	@Test
	public void testGetTesserePermessoScoperte() {
		RegioneDTO regioneDTO=new RegioneDTO();
		ArrayList<TesseraPermessoDTO> tesserePermessoScoperte=new ArrayList<>();
		for(TesseraPermesso t: gameState.getRegioni().get(0).getTesserePermessoScoperte()){
			TesseraPermessoDTO tp=new TesseraPermessoDTO();
			tp.inizializza(t);
			tesserePermessoScoperte.add(tp);
		}
		regioneDTO.setTesserePermessoScoperte(tesserePermessoScoperte);
		assertTrue(tesserePermessoScoperte==regioneDTO.getTesserePermessoScoperte());
	}

	@Test
	public void testSetTesserePermessoScoperte() {
		RegioneDTO regioneDTO=new RegioneDTO();
		ArrayList<TesseraPermessoDTO> tesserePermessoScoperte=new ArrayList<>();
		for(TesseraPermesso t: gameState.getRegioni().get(0).getTesserePermessoScoperte()){
			TesseraPermessoDTO tp=new TesseraPermessoDTO();
			tp.inizializza(t);
			tesserePermessoScoperte.add(tp);
		}
		regioneDTO.setTesserePermessoScoperte(tesserePermessoScoperte);
		assertTrue(tesserePermessoScoperte==regioneDTO.getTesserePermessoScoperte());
	}

	@Test
	public void testGetBonusRegione() {
		RegioneDTO regioneDTO=new RegioneDTO();
		
		Bonus bonus=new BonusPuntiVittoria(2);
		regioneDTO.setBonusRegione(bonus);
		assertTrue(bonus==regioneDTO.getBonusRegione());
	}

	@Test
	public void testSetBonusRegione() {
		RegioneDTO regioneDTO=new RegioneDTO();
		
		Bonus bonus=new BonusPuntiVittoria(2);
		regioneDTO.setBonusRegione(bonus);
		assertTrue(bonus==regioneDTO.getBonusRegione());
	}

	@Test
	public void testGetBalcone() {
		RegioneDTO regioneDTO=new RegioneDTO();
		BalconeDTO balcone=new BalconeDTO();
		balcone.inizializza(gameState.getRegioni().get(0).getBalcone());
		regioneDTO.setBalcone(balcone);		
		assertTrue(balcone==regioneDTO.getBalcone());
		}

	@Test
	public void testSetBalcone() {
		RegioneDTO regioneDTO=new RegioneDTO();
		BalconeDTO balcone=new BalconeDTO();
		balcone.inizializza(gameState.getRegioni().get(0).getBalcone());
		regioneDTO.setBalcone(balcone);		
		assertTrue(balcone==regioneDTO.getBalcone());
		}

	@Test
	public void testInizializza() {
		RegioneDTO regioneDTO=new RegioneDTO();
		regioneDTO.inizializza(gameState.getRegioni().get(0));
		
		assertEquals(gameState.getRegioni().get(0).getNome(), regioneDTO.getNome());
		assertTrue(gameState.getRegioni().get(0).getBonusRegione()==regioneDTO.getBonusRegione());
	}

}
