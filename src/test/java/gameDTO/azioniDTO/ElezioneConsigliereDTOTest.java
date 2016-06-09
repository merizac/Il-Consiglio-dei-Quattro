package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Balcone;
import game.Consigliere;
import game.GameState;
import game.Giocatore;
import game.azioni.ElezioneConsigliere;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.BalconeDTO;
import gameDTO.gameDTO.ConsigliereDTO;

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
