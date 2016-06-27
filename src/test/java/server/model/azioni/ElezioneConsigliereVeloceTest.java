package server.model.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.azioniVeloci.ElezioneConsigliereVeloce;
import server.model.game.Aiutante;
import server.model.game.Colore;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;

public class ElezioneConsigliereVeloceTest {

	static GameState gameState;
	static Regione regione;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
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
		int aiutantiGiocatoreCorrente=gameState.getGiocatoreCorrente().getAiutanti().getAiutante();
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		Consigliere consigliere=regione.getBalcone().getConsigliere().element();
		Consigliere consigliereAggiungere=new Consigliere(new Colore("A"));
		elezione.setConsigliere(consigliereAggiungere);
		elezione.setBalcone(gameState.getRegioni().get(0).getBalcone());
		elezione.eseguiAzione(gameState);
		
		assertTrue(gameState.getConsiglieri().contains(consigliere));
		assertTrue(regione.getBalcone().getConsigliere().contains(consigliereAggiungere));
		assertTrue(gameState.getGiocatoreCorrente().getAiutanti().getAiutante()==aiutantiGiocatoreCorrente-1);
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

}
