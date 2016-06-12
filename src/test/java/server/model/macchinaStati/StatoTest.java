package server.model.macchinaStati;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

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
		gameState.start(giocatori);
		
	}
	
/*	@Test
	public void azioniNonEseguibiliTransizioneExitConUnGiocatoreandNotLastGiro() {
		notify.clear();
		Giocatore giocatore=gameState.getGiocatoreCorrente();
		StartEnd startEnd=new StartEnd(gameState);
		gameState.setUltimoGiro(false);
		while(gameState.getGiocatori().size()>1){
			gameState.getGiocatori().remove(gameState.getGiocatori().size()-1);
		}
		
	}*/
	
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

}
