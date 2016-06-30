package server.model.azioni.azioniVeloci;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.ElezioneConsigliereVeloceDTO;
import server.model.azioni.azioniVeloci.ElezioneConsigliereVeloce;
import server.model.game.Aiutante;
import server.model.game.Colore;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class ElezioneConsigliereVeloceTest {

	static GameState gameState;
	static Regione regione;
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

//	@Test(expected=NullPointerException.class)
	public void testEseguiAzioneConAiutantiInsufficienti() {
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(0));
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		elezione.eseguiAzione(gameState);		
	}

	@Test
	public void testEseguiAzione() {
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		Consigliere consigliere=regione.getBalcone().getConsigliere().element();
		Consigliere consigliereAggiungere=new Consigliere(new Colore("A"));
		elezione.setConsigliere(consigliereAggiungere);
		elezione.setBalcone(gameState.getRegioni().get(0).getBalcone());
		elezione.eseguiAzione(gameState);
		
		assertTrue(gameState.getConsiglieri().contains(consigliere));
		assertTrue(regione.getBalcone().getConsigliere().contains(consigliereAggiungere));
		assertTrue(gameState.getGiocatoreCorrente().getAiutanti().getAiutante()==0);
	}	
	
	@Test
	public void testEseguiAzioneFail() {
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(0));
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		notify.clear();
		elezione.eseguiAzione(gameState);
		
		assertEquals(1, notify.size());
		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test
	public void testGetBalcone() {
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		elezione.setBalcone(regione.getBalcone());
		elezione.setConsigliere(new Consigliere(new Colore("A")));
		
		assertTrue(regione.getBalcone()==elezione.getRegione());
	}

	@Test(expected=NullPointerException.class)
	public void testSetBalcone() {
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		elezione.setBalcone(null);
		elezione.setConsigliere(new Consigliere(new Colore("A")));
	}

	@Test
	public void testGetConsigliere() {
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		elezione.setBalcone(regione.getBalcone());
		Consigliere consigliere=new Consigliere(new Colore("A"));
		elezione.setConsigliere(consigliere);
		
		assertTrue(consigliere==elezione.getConsigliere());
	}

	@Test(expected=NullPointerException.class)
	public void testSetConsigliere() {
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		elezione.setBalcone(regione.getBalcone());
		elezione.setConsigliere(null);
	}
	
	@Test
	public void testGetAzioneDTO() {
		assertTrue(new ElezioneConsigliereVeloce().getAzioneDTO() instanceof ElezioneConsigliereVeloceDTO);
	}

}
