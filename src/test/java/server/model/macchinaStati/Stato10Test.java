package server.model.macchinaStati;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

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

public class Stato10Test {

	static GameState gameState;
	static List<Notify> notify;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		notify = new ArrayList<>();
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

	@Test
	public void testStato10() {
		notify.clear();
		Stato10 stato10=new Stato10(gameState);
		gameState.setStato(stato10);
		assertTrue(gameState.getStato() instanceof Stato10);
		assertTrue(notify.get(0) instanceof MessageNotify);
		assertTrue(notify.get(1) instanceof AzioniNotify);
	}

/*	@Test
	public void testTransizionePrincipaleBonusAzionePrincipaleFalseAndNotUltimoGiroTrue() {
		Stato10 stato10=new Stato10(gameState);
		
		gameState.setBonusAzionePrincipale(false);
		gameState.setUltimoGiro(true);
		
		stato10.transizionePrincipale(gameState);
		
		fail();
	}*/
	
	@Test
	public void testTransizionePrincipaleBonusAzionePrincipaleFalseAndUltimoGiroTrueAndnotMarket() {
		Stato10 stato10=new Stato10(gameState);
		gameState.getGiocatori().add(new Giocatore("A"));
		gameState.setBonusAzionePrincipale(false);
		gameState.setUltimoGiro(false);
		gameState.setNumeroTurni(gameState.getGiocatori().size());
		gameState.setStato(stato10);
		
		stato10.transizionePrincipale(gameState);
		
		assertTrue(gameState.getStato() instanceof StartEnd);
	}	
	
	@Test
	public void testTransizionePrincipaleBonusAzionePrincipaleFalseAndUltimoGiroTrueAndMarket() {
		Stato10 stato10=new Stato10(gameState);
		gameState.getGiocatori().add(new Giocatore("A"));
		gameState.setBonusAzionePrincipale(false);
		gameState.setUltimoGiro(false);
		gameState.setNumeroTurni(100);
		gameState.setStato(stato10);
		
		stato10.transizionePrincipale(gameState);
		
		assertTrue(gameState.getStato() instanceof StartEnd);
	}
	
	@Test
	public void testTransizionePrincipaleBonusAzionePrincipaleTrue() {
		Stato10 stato10=new Stato10(gameState);
		gameState.setBonusAzionePrincipale(true);
		
		stato10.transizionePrincipale(gameState);
		
		assertTrue(gameState.getStato() instanceof Stato10);
	}

	@Test
	public void testTransizioneBonus() {
		Stato10 stato10=new Stato10(gameState);
		stato10.transizioneBonus(gameState);
		assertTrue(gameState.getStato() instanceof StatoBonus);
	}

	@Test
	public void testGetAzioni() {
		Stato10 stato10=new Stato10(gameState);
		gameState.setStato(stato10);
	//	assertTrue(stato10.getAzioni().size()==4);
		assertTrue(stato10.getAzioni().get(0) instanceof ElezioneConsigliere);
		assertTrue(stato10.getAzioni().get(1) instanceof AcquistoTesseraPermesso);
		assertTrue(stato10.getAzioni().get(2) instanceof CostruzioneTesseraPermesso);
		assertTrue(stato10.getAzioni().get(3) instanceof CostruzioneAiutoRe);
	}
	
	@Test
	public void testTransizioneVeloce() {
		notify.clear();
		Stato10 stato10=new Stato10(gameState);
		stato10.transizioneSecondaPrincipale(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizioneSecondaPrincipale() {
		notify.clear();
		Stato10 stato10=new Stato10(gameState);
		stato10.transizioneSecondaPrincipale(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizionePescaCarta() {
		notify.clear();
		Stato10 stato10=new Stato10(gameState);
		stato10.transizioneSecondaPrincipale(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizioneOfferta() {
		notify.clear();
		Stato10 stato10=new Stato10(gameState);
		stato10.transizioneSecondaPrincipale(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void testTransizionePassa() {
		notify.clear();
		Stato10 stato10=new Stato10(gameState);
		stato10.transizioneSecondaPrincipale(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}

}
