package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Consigliere;
import game.GameState;
import game.Giocatore;
import game.Regione;
import game.azioni.ElezioneConsigliere;
import game.azioni.ElezioneConsigliereVeloce;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.BalconeDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.RegioneDTO;

public class ElezioneConsigliereVeloceDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	static Consigliere consigliere;
	static ConsigliereDTO consigliereDTO;
	static Regione regione;
	static RegioneDTO regioneDTO;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		consigliereDTO=new ConsigliereDTO();
		consigliere=gameState.getConsiglieri().get(0);
		consigliereDTO.inizializza(consigliere);
		regioneDTO=new RegioneDTO();
		regione=gameState.getRegioni().get(0);
		regioneDTO.inizializza(regione);

		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetRegione() {
		ElezioneConsigliereVeloceDTO elezione=new ElezioneConsigliereVeloceDTO();
		elezione.setRegione(regioneDTO);
		assertTrue(regioneDTO==elezione.getRegione());
	}

	@Test
	public void testSetRegione() {
		ElezioneConsigliereVeloceDTO elezione=new ElezioneConsigliereVeloceDTO();
		elezione.setRegione(regioneDTO);
		assertTrue(regioneDTO==elezione.getRegione());
	}

	@Test
	public void testGetConsigliere() {
		ElezioneConsigliereVeloceDTO elezione=new ElezioneConsigliereVeloceDTO();
		elezione.setConsigliere(consigliereDTO);;
		assertTrue(consigliereDTO==elezione.getConsigliere());
	}

	@Test
	public void testSetConsigliere() {
		ElezioneConsigliereVeloceDTO elezione=new ElezioneConsigliereVeloceDTO();
		elezione.setConsigliere(consigliereDTO);;
		assertTrue(consigliereDTO==elezione.getConsigliere());
	}

	@Test
	public void testAccept() {
		ElezioneConsigliereVeloceDTO elezione=new ElezioneConsigliereVeloceDTO();
		elezione.setConsigliere(consigliereDTO);
		elezione.setRegione(regioneDTO);
		
		ElezioneConsigliereVeloce azioneParser=(ElezioneConsigliereVeloce) elezione.accept(visitor);
		
		assertTrue(azioneParser.getConsigliere()==consigliere);
		assertTrue(azioneParser.getRegione()==regione);
	}

}
