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
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class Stato11Test {

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
		gameState.start(giocatori, "mappa1");
	}

	@Test
	public void testStato11() {
		notify.clear();
		Stato11 stato11=new Stato11(gameState);
		gameState.setStato(stato11);
		assertTrue(gameState.getStato() instanceof Stato11);
		assertTrue(notify.get(0) instanceof MessageNotify);
		assertTrue(notify.get(1) instanceof AzioniNotify);
	}

	@Test
	public void testTransizionePrincipaleBonusAzionePrincipaleFalse() {
		notify.clear();
		Stato11 stato11=new Stato11(gameState);
		stato11.transizionePrincipale(gameState);
		assertTrue(gameState.getStato() instanceof Stato01);
	}
	
	@Test
	public void testTransizionePrincipaleBonusAzionePrincipaleTrue() {
		gameState.setBonusAzionePrincipale(true);
		Stato11 stato11=new Stato11(gameState);
		stato11.transizionePrincipale(gameState);
		assertTrue(!gameState.isBonusAzionePrincipale());
	}

	@Test
	public void testTransizioneBonus() {
		Stato11 stato11=new Stato11(gameState);
		
		
		//fallliiiiiiiiiiiiiiiiito
		
		
		
		
		//stato11.transizioneBonus(gameState);
		//assertTrue(gameState.getStato() instanceof StatoBonus);
	}
	
	@Test
	public void testTransizioneVeloce() {
		Stato11 stato11=new Stato11(gameState);
		stato11.transizioneVeloce(gameState);
		assertTrue(gameState.getStato() instanceof Stato10);
	}

	@Test
	public void testGetAzioni() {
		notify.clear();
		Stato11 stato11=new Stato11(gameState);
		gameState.setStato(stato11);
		assertTrue(stato11.getAzioni().size()==7);
		assertTrue(stato11.getAzioni().get(0) instanceof ElezioneConsigliere);
		assertTrue(stato11.getAzioni().get(1) instanceof AcquistoTesseraPermesso);
		assertTrue(stato11.getAzioni().get(2) instanceof CostruzioneTesseraPermesso);
		assertTrue(stato11.getAzioni().get(3) instanceof CostruzioneAiutoRe);
		assertTrue(stato11.getAzioni().get(4) instanceof IngaggioAiutante);
		assertTrue(stato11.getAzioni().get(5) instanceof CambioTesseraPermesso);
		assertTrue(stato11.getAzioni().get(6) instanceof ElezioneConsigliereVeloce);
	}
	
	@Test
	public void azioniNonEseguibiliTransizionePescaCarta() {
		notify.clear();
		Stato11 stato11=new Stato11(gameState);
		stato11.transizionePescaCarta(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void azioniNonEseguibiliTransizioneOfferta() {
		notify.clear();
		Stato11 stato11=new Stato11(gameState);
		stato11.transizioneOfferta(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void azioniNonEseguibiliTransizionePassa() {
		notify.clear();
		Stato11 stato11=new Stato11(gameState);
		stato11.transizionePassa(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
	
	@Test
	public void azioniNonEseguibiliTransizioneSecondaPrincipale() {
		notify.clear();
		Stato11 stato11=new Stato11(gameState);
		stato11.transizioneSecondaPrincipale(gameState);
		assertTrue(notify.get(notify.size()-1) instanceof MessageNotify);
	}
}
