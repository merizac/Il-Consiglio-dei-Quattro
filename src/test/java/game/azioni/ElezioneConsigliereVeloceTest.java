package game.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Aiutante;
import game.Colore;
import game.Consigliere;
import game.GameState;
import game.Giocatore;
import game.Regione;

public class ElezioneConsigliereVeloceTest {

	static GameState gameState;
	static Regione regione;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
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
		elezione.setRegione(regione);
		elezione.eseguiAzione(gameState);
		
		assertTrue(gameState.getConsiglieri().contains(consigliere));
		assertTrue(regione.getBalcone().getConsigliere().contains(consigliereAggiungere));
		assertTrue(gameState.getGiocatoreCorrente().getAiutanti().getAiutante()==aiutantiGiocatoreCorrente-1);
	}

	@Test
	public void testGetRegione() {
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		elezione.setRegione(regione);
		elezione.setConsigliere(new Consigliere(new Colore("A")));
		
		assertTrue(regione==elezione.getRegione());
	}

	@Test(expected=NullPointerException.class)
	public void testSetRegione() {
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		elezione.setRegione(null);
		elezione.setConsigliere(new Consigliere(new Colore("A")));
	}

	@Test
	public void testGetConsigliere() {
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		elezione.setRegione(regione);
		Consigliere consigliere=new Consigliere(new Colore("A"));
		elezione.setConsigliere(consigliere);
		
		assertTrue(consigliere==elezione.getConsigliere());
	}

	@Test(expected=NullPointerException.class)
	public void testSetConsigliere() {
		ElezioneConsigliereVeloce elezione=new ElezioneConsigliereVeloce();
		elezione.setRegione(regione);
		elezione.setConsigliere(null);
	}

}