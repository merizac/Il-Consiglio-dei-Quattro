package game.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.CartaPolitica;
import game.Colore;
import game.GameState;
import game.Giocatore;
import game.Regione;
import game.TesseraPermesso;

public class AcquistoTesseraPermessoTest {

	static GameState gameState;
	static Regione regione;
	static ArrayList<CartaPolitica> carteGiocatore;
	static int indiceTesseraScoperta;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState.start();
		indiceTesseraScoperta=0;
		regione=gameState.getRegioni().get(0);
		Giocatore giocatore=new Giocatore("Bello");	
		gameState.setGiocatoreCorrente(giocatore);
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
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(10);
		ArrayList<CartaPolitica> carte=new ArrayList<>();
		carte.add(new CartaPolitica(new Colore("Multicolore")));
		carte.add(new CartaPolitica(new Colore("Multicolore")));
		carte.add(new CartaPolitica(regione.getBalcone().getConsigliere().element().getColore()));

		azione.setCarteGiocatore(carte);
		azione.setIndiceTesseraScoperta(indiceTesseraScoperta);
		TesseraPermesso tesseraPermesso=regione.getTesserePermessoScoperte().get(indiceTesseraScoperta);
		azione.eseguiAzione(gameState);
		
		assertEquals(4, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertTrue(tesseraPermesso==gameState.getGiocatoreCorrente().getTesserePermesso().get(gameState.getGiocatoreCorrente().getTesserePermesso().size()-1));
//		assertTrue(gameState.getMazzoCartePolitica().getCarte().contains(carte));
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
