package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.AiutanteDTO;
import server.model.azioni.azioniMarket.AzioneOfferta;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import utility.ParameterException;

public class AzioneOffertaDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;


	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetPrezzo() {
		AzioneOffertaDTO azione=new AzioneOffertaDTO();
		azione.setPrezzo(2);
		
		assertEquals(2, azione.getPrezzo());
	}

	@Test
	public void testGetMarketableDTO() {
		AiutanteDTO aiutanteDTO=new AiutanteDTO();
		aiutanteDTO.inizializza(new Aiutante(1));
		AzioneOffertaDTO azione=new AzioneOffertaDTO();
		azione.setMarketableDTO(aiutanteDTO);
		
		assertTrue(aiutanteDTO==azione.getMarketableDTO());
	}

	@Test
	public void testAccept() throws ParameterException {
		AzioneOffertaDTO azione=new AzioneOffertaDTO();
		Aiutante aiutante=new Aiutante(1);
		AiutanteDTO aiutanteDTO=new AiutanteDTO();
		aiutanteDTO.inizializza(aiutante);
		azione.setPrezzo(1);
		azione.setMarketableDTO(aiutanteDTO);
		AzioneOfferta real=new AzioneOfferta();
		real.setMarketable(aiutante);
		real.setPrezzo(1);
		
		AzioneOfferta parser=(AzioneOfferta) azione.accept(visitor);
		
		assertTrue(parser instanceof AzioneOfferta);
	}

	@Test
	public void testToString() {
		AzioneOffertaDTO azione=new AzioneOffertaDTO();
		assertEquals("Per fare un'offerta  [Offerta] ", azione.toString());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetPrezzoMinoreUgualeA0() {
		AzioneOffertaDTO azione=new AzioneOffertaDTO();
		azione.setPrezzo(0);
	}

	@Test
	public void testSetMarketableDTO() {
		AiutanteDTO aiutanteDTO=new AiutanteDTO();
		aiutanteDTO.inizializza(new Aiutante(1));
		AzioneOffertaDTO azione=new AzioneOffertaDTO();
		azione.setMarketableDTO(aiutanteDTO);
		
		assertTrue(aiutanteDTO==azione.getMarketableDTO());
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetMarketableDTONull() {
		AzioneOffertaDTO azione=new AzioneOffertaDTO();
		azione.setMarketableDTO(null);
	}

}
