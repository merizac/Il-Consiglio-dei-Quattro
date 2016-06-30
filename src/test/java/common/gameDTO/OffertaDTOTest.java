package common.gameDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.market.Offerta;

public class OffertaDTOTest {

	static GameState gameState;
	static GameStateDTO gameStateDTO;
	static Giocatore giocatore;
	static GiocatoreDTO giocatoreDTO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatore = new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState = new GameState();
		gameState.start(giocatori, "mappa1");
		giocatoreDTO=new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
		
		gameStateDTO=new GameStateDTO();
		gameStateDTO.inizializza(gameState);
	}

	@Test
	public void testGetMarketableDTO() {
		OffertaDTO offerta=new OffertaDTO();
		AiutanteDTO aiutante=new AiutanteDTO();
		aiutante.setAiutanti(2);
		offerta.setMarketableDTO(aiutante);
		
		assertTrue(offerta.getMarketableDTO()==aiutante);
	}

	@Test
	public void testSetMarketableDTO() {
		OffertaDTO offerta=new OffertaDTO();
		AiutanteDTO aiutante=new AiutanteDTO();
		aiutante.setAiutanti(2);
		offerta.setMarketableDTO(aiutante);
		
		assertTrue(offerta.getMarketableDTO()==aiutante);
	}

	@Test
	public void testGetPrezzo() {
		OffertaDTO offerta=new OffertaDTO();
		offerta.setPrezzo(2);
		
		assertEquals(2, offerta.getPrezzo());
	}

	@Test
	public void testGetGiocatoreDTO() {
		OffertaDTO offerta=new OffertaDTO();
		offerta.setGiocatoreDTO(giocatoreDTO);
		
		assertTrue(giocatoreDTO==offerta.getGiocatoreDTO());
	}

	@Test
	public void testSetGiocatoreDTO() {
		OffertaDTO offerta=new OffertaDTO();
		offerta.setGiocatoreDTO(giocatoreDTO);
		
		assertTrue(giocatoreDTO==offerta.getGiocatoreDTO());
	}

	@Test
	public void testSetPrezzo() {
		OffertaDTO offerta=new OffertaDTO();
		offerta.setPrezzo(2);
		
		assertEquals(2, offerta.getPrezzo());
	}

	@Test
	public void testInizializza() {
		OffertaDTO offertaDTO=new OffertaDTO();
		Offerta offerta=new Offerta(giocatore, new Aiutante(1), 3);
		
		offertaDTO.inizializza(offerta);
		
		assertEquals("Giocatore", offertaDTO.getGiocatoreDTO().getNome());
		assertTrue(offerta.getMarketable()!=null);
		assertEquals(3, offertaDTO.getPrezzo());
	}

}
