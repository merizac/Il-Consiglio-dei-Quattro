package server.model.macchinaStati;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.Passa;
import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import server.model.azioni.azioniPrincipali.CostruzioneAiutoRe;
import server.model.azioni.azioniPrincipali.CostruzioneTesseraPermesso;
import server.model.azioni.azioniPrincipali.ElezioneConsigliere;
import server.model.azioni.azioniVeloci.CambioTesseraPermesso;
import server.model.azioni.azioniVeloci.ElezioneConsigliereVeloce;
import server.model.azioni.azioniVeloci.IngaggioAiutante;
import server.model.azioni.azioniVeloci.SecondaAzionePrincipale;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class Stato01Test {

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
	public void testStato01() {
		notify.clear();
		Stato01 stato01=new Stato01(gameState);
		gameState.setStato(stato01);
		assertTrue(gameState.getStato() instanceof Stato01);
		assertTrue(notify.get(0) instanceof MessageNotify);
		assertTrue(notify.get(1) instanceof AzioniNotify);
	}


	@Test
	public void testTransizioneVeloceUltimoGiroANDNextPlayerNotTheLast() {
		Stato01 stato01=new Stato01(gameState);
		gameState.setUltimoGiro(true);
		gameState.getGiocatori().add(new Giocatore("A"));
		gameState.getGiocatori().add(new Giocatore("B"));
		gameState.getGiocatori().add(new Giocatore("C"));
		
		stato01.transizioneVeloce(gameState);
		
		assertTrue(gameState.getStato() instanceof StartEnd);
	}
	
/*	@Test
	public void testTransizioneVeloceUltimoGiroANDNextPlayerIsTheLast() {
		notify.clear();
		Stato01 stato01=new Stato01(gameState);
		gameState.setUltimoGiro(false);
		
		stato01.transizioneVeloce(gameState);
		
		assertTrue(gameState.getStato() instanceof StatoFinePartita);
	}*/
	
	@Test
	public void testTransizioneVeloceNotUltimoGiroANDNotMarket() {
		Stato01 stato01=new Stato01(gameState);
		gameState.setUltimoGiro(false);
		gameState.setNumeroTurni(gameState.getGiocatori().size());
		stato01.transizioneVeloce(gameState);
		
		assertTrue(gameState.getStato() instanceof StartEnd);
	}
	
	@Test
	public void testTransizioneVeloceNotUltimoGiroANDMarket() {
		Stato01 stato01=new Stato01(gameState);
		gameState.setUltimoGiro(false);
		
		stato01.transizioneVeloce(gameState);
		
		assertTrue(gameState.getStato() instanceof StatoOffertaMarket);
	}
	

	@Test
	public void testTransizioneSecondaPrincipale() {
		Stato01 stato01=new Stato01(gameState);
		
		stato01.transizioneSecondaPrincipale(gameState);
		
		assertTrue(gameState.getStato() instanceof Stato10);
	}

	@Test
	public void testTransizionePassa() {
		Stato01 stato01=new Stato01(gameState);
		
		stato01.transizioneVeloce(gameState);
	}

	@Test
	public void testGetAzioni() {
		notify.clear();
		Stato01 stato01=new Stato01(gameState);
		gameState.setStato(stato01);
		assertTrue(stato01.getAzioni().size()==5);
		assertTrue(stato01.getAzioni().get(0) instanceof IngaggioAiutante);
		assertTrue(stato01.getAzioni().get(1) instanceof CambioTesseraPermesso);
		assertTrue(stato01.getAzioni().get(2) instanceof ElezioneConsigliereVeloce);
		assertTrue(stato01.getAzioni().get(3) instanceof SecondaAzionePrincipale);
		assertTrue(stato01.getAzioni().get(4) instanceof Passa);
	}

	@Test
	public void azioniNonEseguibiliTransizionePrincipale() {
		notify.clear();
		Stato01 stato01=new Stato01(gameState);
		stato01.transizionePrincipale(gameState);
 
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void azioniNonEseguibiliTransizionePescaCarta() {
		notify.clear();
		Stato01 stato01=new Stato01(gameState);
		stato01.transizionePescaCarta(gameState);
 
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}	
	
	@Test
	public void azioniNonEseguibiliTransizioneOfferta() {
		notify.clear();
		Stato01 stato01=new Stato01(gameState);
		stato01.transizioneOfferta(gameState);
 
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}	
	
	@Test
	public void azioniNonEseguibiliTransizioneBonus() {
		notify.clear();
		Stato01 stato01=new Stato01(gameState);
		stato01.transizioneBonus(gameState);
 
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
}
