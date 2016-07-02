package server.model.game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Aiutante;
import server.model.game.CartaPolitica;
import server.model.game.Colore;
import server.model.game.Emporio;
import server.model.game.Giocatore;
import server.model.game.Mazzo;
import server.model.game.PunteggioNobiltà;
import server.model.game.Reader;

public class GiocatoreTest {
	
	static Giocatore giocatore;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Emporio> empori = new ArrayList<>();
		for(int i=0; i< 7; i++){
			empori.add(new Emporio(new Colore("giallo")));
		}
		Reader reader= new Reader();
		Mazzo<CartaPolitica> mazzo = reader.letturaCartePolitica();
		giocatore = new Giocatore("nome");
		giocatore.setColoreGiocatore(new Colore("giallo"));
		giocatore.setAiutanti(new Aiutante(4));
		giocatore.getCartePolitica().addAll(mazzo.getCarte());
		giocatore.setPunteggioVittoria(3);
		giocatore.setPunteggioRicchezza(10);
		giocatore.setPunteggioNobiltà(new PunteggioNobiltà(10, null));
		giocatore.getEmpori().addAll(empori);

	}

	@Test
	public void testAumentaRicchezza() {
		
		int monete= 5;
		int punteggio = giocatore.getPunteggioRicchezza()+monete;
		giocatore.aumentaRicchezza(monete);
		assertEquals(punteggio, giocatore.getPunteggioRicchezza());
	}

	@Test
	public void testDiminuisciRicchezza() {
		int monete = 7;
		int punteggio=giocatore.getPunteggioRicchezza()-monete;
		giocatore.diminuisciRicchezza(monete);
		assertEquals(punteggio, giocatore.getPunteggioRicchezza());
	}

	@Test
	public void testAumentaPuntiVittoria() {
		int puntiVittoria=4;
		giocatore.aumentaPuntiVittoria(puntiVittoria);
		assertEquals(7, giocatore.getPunteggioVittoria());
		
	}

	@Test
	public void testAggiungiCartaPolitica() {
		CartaPolitica cartaDaAggiungere = new CartaPolitica(new Colore("verde"));
		giocatore.aggiungiCartaPolitica(cartaDaAggiungere);
		assertTrue(giocatore.getCartePolitica().contains(cartaDaAggiungere));
	}

	@Test
	public void testRimuoviCartaPolitica() {
		CartaPolitica cartaPolitica = new CartaPolitica(new Colore("A"));
		giocatore.rimuoviCartaPolitica(cartaPolitica);
		assertFalse(giocatore.getCartePolitica().contains(cartaPolitica));
	}
	
	@Test
	public void testEqualsColoreUguale(){
		Giocatore giocatoreTest= new Giocatore(giocatore.getNome());
		giocatoreTest.setColoreGiocatore(giocatore.getColoreGiocatore());
		assertTrue(giocatore.equals(giocatoreTest));
	}
	
	@Test
	public void testEqualsNull(){
		assertFalse(giocatore.equals(null));
	}
	
	@Test
	public void testEqualsNonGiocatore(){
		assertFalse(giocatore.equals(new Object()));
	}

}
