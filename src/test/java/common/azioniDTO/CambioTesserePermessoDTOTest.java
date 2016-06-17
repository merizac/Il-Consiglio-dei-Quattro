package common.azioniDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.RegioneDTO;
import server.model.azioni.azioniVeloci.CambioTesseraPermesso;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import utility.ParameterException;

public class CambioTesserePermessoDTOTest {

	static GameState gameState;
	static Regione regione;
	static RegioneDTO regioneDTO;
	static AzioneVisitor visitor;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		regione=gameState.getRegioni().get(0);
		regioneDTO=new RegioneDTO();
		regioneDTO.inizializza(regione);
		
		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetRegione() {
		CambioTesserePermessoDTO cambio=new CambioTesserePermessoDTO();
		cambio.setRegione(regioneDTO);
		assertTrue(regioneDTO==cambio.getRegione());
	}

	@Test
	public void testSetRegione() {
		CambioTesserePermessoDTO cambio=new CambioTesserePermessoDTO();
		cambio.setRegione(regioneDTO);
		assertTrue(regioneDTO==cambio.getRegione());
	}

	@Test
	public void testAccept() throws ParameterException {
		CambioTesserePermessoDTO cambio=new CambioTesserePermessoDTO();
		cambio.setRegione(regioneDTO);
		
		CambioTesseraPermesso azioneVisitor=(CambioTesseraPermesso)cambio.accept(visitor);
		
		assertTrue(regione==azioneVisitor.getRegione());
	}

}
