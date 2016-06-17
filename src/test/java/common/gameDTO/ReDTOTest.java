package common.gameDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.gameDTO.CittàDTO;
import common.gameDTO.ReDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Re;

public class ReDTOTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
	}

	@Test
	public void testGetCittà() {
		ReDTO reDTO=new ReDTO();
		Re re=gameState.getPedinaRe();
		CittàDTO cittàDTO=new CittàDTO();
		cittàDTO.inizializza(re.getCittà());
		reDTO.setCittà(cittàDTO);
		assertEquals(re.getCittà().getNome(), reDTO.getCittà().getNome());
	}

	@Test
	public void testSetCittà() {
		ReDTO reDTO=new ReDTO();
		Re re=gameState.getPedinaRe();
		CittàDTO cittàDTO=new CittàDTO();
		cittàDTO.inizializza(re.getCittà());
		reDTO.setCittà(cittàDTO);
		assertEquals(re.getCittà().getNome(), reDTO.getCittà().getNome());
	}
	
	@Test
	public void testInizializzazioneReDTO(){
		ReDTO reDTO=new ReDTO();
		Re re=gameState.getPedinaRe();
		reDTO.inizializza(re);
		assertEquals(re.getCittà().getNome(), reDTO.getCittà().getNome());
	}

}
