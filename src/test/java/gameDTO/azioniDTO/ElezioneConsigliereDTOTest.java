package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.ElezioneConsigliereDTO;
import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.BalconeDTO;
import common.gameDTO.ConsigliereDTO;
import server.model.azioni.azioniPrincipali.ElezioneConsigliere;
import server.model.game.Balcone;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ElezioneConsigliereDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	static Consigliere consigliere;
	static ConsigliereDTO consigliereDTO;
	static Balcone balcone;
	static BalconeDTO balconeDTO;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		consigliereDTO=new ConsigliereDTO();
		balconeDTO=new BalconeDTO();
		balcone=gameState.getRegioni().get(0).getBalcone();
		consigliere=gameState.getConsiglieri().get(0);
		consigliereDTO.inizializza(consigliere);
		balconeDTO.inizializza(balcone);
		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetConsigliereDTO() {
		ElezioneConsigliereDTO elezione=new ElezioneConsigliereDTO();
		elezione.setConsigliereDTO(consigliereDTO);
		
		assertTrue(consigliereDTO==elezione.getConsigliereDTO());
	}

	@Test
	public void testGetBalconeDTO() {
		ElezioneConsigliereDTO elezione=new ElezioneConsigliereDTO();
		elezione.setBalconeDTO(balconeDTO);

		assertTrue(balconeDTO==elezione.getBalconeDTO());
	}

	@Test
	public void testSetConsigliereDTO() {
		ElezioneConsigliereDTO elezione=new ElezioneConsigliereDTO();
		elezione.setConsigliereDTO(consigliereDTO);
		
		assertTrue(consigliereDTO==elezione.getConsigliereDTO());
	}

	@Test
	public void testSetBalconeDTO() {
		ElezioneConsigliereDTO elezione=new ElezioneConsigliereDTO();
		elezione.setBalconeDTO(balconeDTO);

		assertTrue(balconeDTO==elezione.getBalconeDTO());	}
	
	@Test
	public void testAccept() {
		ElezioneConsigliereDTO elezione=new ElezioneConsigliereDTO();
		elezione.setConsigliereDTO(consigliereDTO);
		elezione.setBalconeDTO(balconeDTO);
		
		ElezioneConsigliere azioneParser=(ElezioneConsigliere) elezione.accept(visitor);
		
		assertTrue(azioneParser.getBalcone()==balcone);
		assertTrue(azioneParser.getConsigliere()==consigliere);
	}

}
