package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.GiocatoreDTO;
import server.model.azioni.azioniMarket.AzioneAcquisto;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.market.Offerta;
import utility.ParameterException;

public class AzioneAcquistoDTOTest {

	static GameState gameState;
	static GiocatoreDTO giocatoreDTO;
	static AzioneVisitor visitor;
	static Giocatore giocatore;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		giocatoreDTO=new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetOfferta() {
		AzioneAcquistoDTO azione=new AzioneAcquistoDTO();
		azione.setOfferta(1);
		assertEquals(1, azione.getOfferta());
	}

	@Test
	public void testGetGiocatoreDTO() {
		AzioneAcquistoDTO azione=new AzioneAcquistoDTO();
		azione.setOfferta(2);
		assertEquals(2, azione.getOfferta());
	}

	@Test
	public void testAccept() throws ParameterException {
		AzioneAcquistoDTO azioneDTO=new AzioneAcquistoDTO();
		AzioneAcquisto azione=new AzioneAcquisto();
		
		azioneDTO.setOfferta(1);
		azioneDTO.setGiocatoreDTO(giocatoreDTO);
		
		Offerta offerta=new Offerta(new Giocatore("A"), new Aiutante(2), 3);
		gameState.getOfferteMarket().add(offerta);
		
		azione=(AzioneAcquisto) azioneDTO.accept(visitor);
		
		assertEquals(offerta, azione.getOfferta());
		assertEquals(giocatore, azione.getAcquirente());
	}

	@Test
	public void testToString() {
		AzioneAcquistoDTO azione=new AzioneAcquistoDTO();
		assertEquals("Per acquistare  [Acquista]", azione.toString());
	}

	@Test
	public void testSetOfferta() {
		AzioneAcquistoDTO azione=new AzioneAcquistoDTO();
		azione.setOfferta(2);
		assertEquals(2, azione.getOfferta());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetOffertaMinoreUgualeAzero() {
		AzioneAcquistoDTO azione=new AzioneAcquistoDTO();
		azione.setOfferta(0);
	}

	@Test
	public void testSetGiocatoreDTO() {
		AzioneAcquistoDTO azione=new AzioneAcquistoDTO();
		azione.setGiocatoreDTO(giocatoreDTO);
		assertTrue(giocatoreDTO==azione.getGiocatoreDTO());;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetGiocatoreDTONull() {
		AzioneAcquistoDTO azione=new AzioneAcquistoDTO();
		azione.setGiocatoreDTO(null);
	}

}
