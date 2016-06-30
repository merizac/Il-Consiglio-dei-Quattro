package server.model.azioni.azioniVeloci;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.model.azioni.azioniVeloci.CambioTesseraPermesso;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.CambioTesserePermessoDTO;

public class CambioTesseraPermessoTest {

	static Regione regione;
	static GameState gameState;
	static List<Notify> notify;
	
	@BeforeClass
	public static void init() throws IOException{
		notify = new ArrayList<>();
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		Observer<Notify> observer = new Observer<Notify>() {

			@Override
			public void update(Notify c) {
				notify.add(c);
			}
		};
		gameState.registerObserver(observer);
		gameState.start(giocatori, "mappa1");
		regione=gameState.getRegioni().get(0);
	}
		
	@Test
	public void testEseguiAzioneBuonFine() {
		CambioTesseraPermesso cambioTesseraPermesso=new CambioTesseraPermesso();
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(2));
		regione=gameState.getRegioni().get(0);
		TesseraPermesso t1=regione.getTesserePermessoScoperte().get(0);
		TesseraPermesso t2=regione.getTesserePermessoScoperte().get(1);
		cambioTesseraPermesso.setRegione(regione);
		cambioTesseraPermesso.eseguiAzione(gameState);
	
		assertEquals(1, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
		assertNotNull(regione.getTesserePermessoScoperte().get(0));
		assertNotNull(regione.getTesserePermessoScoperte().get(1));
		assertNotEquals(t1,regione.getTesserePermessoScoperte().get(0));
		assertNotEquals(t2,regione.getTesserePermessoScoperte().get(1));
		assertTrue(regione.getMazzoTesserePermesso().getCarte().contains(t1));
		assertTrue(regione.getMazzoTesserePermesso().getCarte().contains(t2));
	}
	
	@Test
	public void testEseguiAzioneFail() {
		CambioTesseraPermesso cambioTesseraPermesso=new CambioTesseraPermesso();
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(0));
		notify.clear();
		cambioTesseraPermesso.eseguiAzione(gameState);
	
		assertEquals(1, notify.size());
		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test 
	public void testGetRegione(){
		CambioTesseraPermesso cambioTesseraPermesso=new CambioTesseraPermesso();
		cambioTesseraPermesso.setRegione(regione);
		
		assertTrue(regione==cambioTesseraPermesso.getRegione());
	}
	
	@Test (expected=NullPointerException.class)
	public void testSetRegione(){
		CambioTesseraPermesso cambioTesseraPermesso=new CambioTesseraPermesso();
		cambioTesseraPermesso.setRegione(null);
	}
	
	@Test 
	public void testGetAzioneDTO(){
		assertTrue(new CambioTesseraPermesso().getAzioneDTO() instanceof CambioTesserePermessoDTO);
	}
	
}
