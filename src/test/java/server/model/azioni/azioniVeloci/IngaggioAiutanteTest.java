package server.model.azioni.azioniVeloci;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import server.model.azioni.azioniVeloci.IngaggioAiutante;
import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class IngaggioAiutanteTest {

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
	}
	
	@Test
	public void testEseguiAzioneFail() {
		IngaggioAiutante ingaggio=new IngaggioAiutante();
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(2));
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(2);

		notify.clear();
		ingaggio.eseguiAzione(gameState);
		
		assertEquals(1, notify.size());
		assertTrue(notify.get(0) instanceof MessageNotify);
		assertEquals(2, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertEquals(2, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
	}

	@Test
	public void testEseguiAzione() {
		IngaggioAiutante ingaggio=new IngaggioAiutante();
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(2));
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(10);
		
		ingaggio.eseguiAzione(gameState);
		
		assertEquals(10-3, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertEquals(2+1, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
	}
	
	@Test 
	public void testGetAzioneDTO(){
		assertTrue(new IngaggioAiutante().getAzioneDTO() instanceof IngaggioAiutanteDTO);
	}

}
