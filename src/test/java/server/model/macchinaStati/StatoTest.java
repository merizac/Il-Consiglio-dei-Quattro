package server.model.macchinaStati;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import server.model.azioni.azioniPrincipali.ElezioneConsigliere;
import server.model.azioni.azioniVeloci.SecondaAzionePrincipale;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.Notify;
import utility.Observer;

public class StatoTest {

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
	public void azioniNonEseguibiliTransizioneExitLastGiroGiocatoriNonFiniti() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		gameState.setUltimoGiro(true);
		
		gameState.getGiocatori().add(new Giocatore("Giocatore1"));
		gameState.getGiocatori().add(new Giocatore("Giocatore2"));
		
		startEnd.transizioneExit(gameState);
		
		assertTrue(gameState.getStato() instanceof StartEnd);
	}
	
	@Test
	public void azioniNonEseguibiliTransizioneExitNotLastGiro() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		gameState.setUltimoGiro(false);
		
		gameState.getGiocatori().add(new Giocatore("Giocatore1"));
		gameState.getGiocatori().add(new Giocatore("Giocatore2"));
		gameState.setNumeroTurni(6);
		startEnd.transizioneExit(gameState);
		
		assertTrue(gameState.getStato() instanceof StartEnd);
	}
	
	@Test
	public void azioniNonEseguibiliTransizioneExitNotLastGiroMarket() {
		notify.clear();
		StartEnd startEnd=new StartEnd(gameState);
		gameState.setUltimoGiro(false);
		
		gameState.getGiocatori().add(new Giocatore("Giocatore1"));
		gameState.getGiocatori().add(new Giocatore("Giocatore2"));
		gameState.setNumeroTurni(gameState.getGiocatori().size());

		startEnd.transizioneExit(gameState);
		
		assertTrue(gameState.getStato() instanceof StatoOffertaMarket);
	}
	
	@Test
	public void testdaEseguireTrue(){
		StartEnd startEnd=new StartEnd(gameState);
		assertTrue(startEnd.daEseguire(Arrays.asList(new AcquistoTesseraPermesso(),new ElezioneConsigliere()), new ElezioneConsigliere()));
	}

	@Test
	public void testdaEseguireFalse(){
		StartEnd startEnd=new StartEnd(gameState);
		assertFalse(startEnd.daEseguire(Arrays.asList(new AcquistoTesseraPermesso(),new ElezioneConsigliere()), new SecondaAzionePrincipale()));
	}

}
