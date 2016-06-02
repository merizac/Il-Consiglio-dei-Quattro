package game.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import bonus.Bonus;
import bonus.BonusAiutanti;
import bonus.BonusAzionePrincipale;
import bonus.BonusCartePolitica;
import bonus.BonusMoneta;
import bonus.BonusPuntiNobiltà;
import bonus.BonusPuntiVittoria;
import game.CartaPolitica;
import game.Colore;
import game.GameState;
import game.Giocatore;
import game.Regione;
import game.TesseraPermesso;
import gameDTO.gameDTO.GameStateDTO;

public class AcquistoTesseraPermessoTest {

	static GameState gameState;
	static Regione regione;
	static ArrayList<CartaPolitica> carteGiocatore;
	static int indiceTesseraScoperta;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		indiceTesseraScoperta=0;
		regione=gameState.getRegioni().get(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void indiceTesseraScopertaMaggioreNumeroTessere(){		
		AcquistoTesseraPermesso acquisto= new AcquistoTesseraPermesso();
		acquisto.setRegione(regione);
		acquisto.setIndiceTesseraScoperta(100);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void indiceTesseraScopertaNegativo(){
		AcquistoTesseraPermesso acquisto= new AcquistoTesseraPermesso();
		acquisto.setRegione(regione);
		acquisto.setIndiceTesseraScoperta(-2);
	}
	

	@Test
	public void testEseguiAzione() {
		AcquistoTesseraPermesso azione=new AcquistoTesseraPermesso();
		azione.setRegione(regione);
		ArrayList<CartaPolitica> carte=new ArrayList<>();
		carte.add(new CartaPolitica(new Colore("Multicolore")));
		carte.add(new CartaPolitica(new Colore("Multicolore")));
		carte.add(new CartaPolitica(regione.getBalcone().getConsigliere().element().getColore()));

		azione.setCarteGiocatore(carte);
		azione.setIndiceTesseraScoperta(indiceTesseraScoperta);
		TesseraPermesso tesseraPermesso=regione.getTesserePermessoScoperte().get(indiceTesseraScoperta);
		
		int aiutanti_prima=gameState.getGiocatoreCorrente().getAiutanti().getAiutante();
		int cartePolitica_prima=gameState.getGiocatoreCorrente().getCartePolitica().size();
		
		azione.eseguiAzione(gameState);
				
		//controllo utilizzo bonus
		int monete=0;
		int aiutanti=0;
		int vittoria=0;
		int nobiltà=0;
		int cartePolitica=0;
		for(Bonus b: tesseraPermesso.getBonus()){
			if (b instanceof BonusMoneta)
				monete=((BonusMoneta) b).getMonete();
			if(b instanceof BonusAiutanti)
				aiutanti= ((BonusAiutanti) b).getAiutanti();
			if(b instanceof BonusAzionePrincipale)										//concludere!!!!!

			if(b instanceof BonusPuntiVittoria)
				vittoria=((BonusMoneta) b).getMonete();
			if(b instanceof BonusCartePolitica)
				cartePolitica=((BonusCartePolitica) b).getCartePolitica();
			if(b instanceof BonusPuntiNobiltà)
				nobiltà=((BonusPuntiNobiltà) b).getPuntiNobiltà();
		}
		//controllo che la tessera permesso venga data al giocatore
		assertTrue(tesseraPermesso==gameState.getGiocatoreCorrente().getTesserePermesso().get(gameState.getGiocatoreCorrente().getTesserePermesso().size()-1));
		//controllo che sia cambiata la carta pescata
		assertNotEquals(tesseraPermesso, gameState.getRegioni().get(0).getTesserePermessoScoperte().get(indiceTesseraScoperta));
		//controllo che vengano scalati e dati puntiricchezza dei bonus
		assertEquals(4+monete, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertEquals(aiutanti_prima+aiutanti, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
//		assertEquals(cartePolitica_prima+cartePolitica, gameState.getGiocatoreCorrente().getCartePolitica().size());
	}	
	

	@Test
	public void testGetCarteGiocatore() {
		AcquistoTesseraPermesso mossa=new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		ArrayList<CartaPolitica> c=new ArrayList<>();
		c.add(new CartaPolitica(new Colore("C1")));
		c.add(new CartaPolitica(new Colore("C2")));
		mossa.setCarteGiocatore(c);
		
		assertTrue(c==mossa.getCarteGiocatore());
	}

	@Test
	public void testSetCarteGiocatore() {
		AcquistoTesseraPermesso mossa=new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		ArrayList<CartaPolitica> c=new ArrayList<>();
		c.add(new CartaPolitica(new Colore("C1")));
		c.add(new CartaPolitica(new Colore("C2")));
		mossa.setCarteGiocatore(c);
		
		assertTrue(c==mossa.getCarteGiocatore());
	}

	@Test
	public void testGetRegione() {
		AcquistoTesseraPermesso mossa=new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		assertTrue(regione==mossa.getRegione());
	}

	@Test
	public void testSetRegione() {
		AcquistoTesseraPermesso mossa=new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		assertTrue(regione==mossa.getRegione());
	}

	@Test
	public void testGetIndiceTesseraScoperta() {
		AcquistoTesseraPermesso mossa=new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		int indice=2;
		mossa.setIndiceTesseraScoperta(indice);
		assertTrue(indice==mossa.getIndiceTesseraScoperta());
	}

	@Test
	public void testSetIndiceTesseraScoperta() {
		AcquistoTesseraPermesso mossa=new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		int indice=1;
		mossa.setIndiceTesseraScoperta(indice);
		assertTrue(indice==mossa.getIndiceTesseraScoperta());
	}
}
