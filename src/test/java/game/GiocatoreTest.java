package game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.cg17.Reader_new;

public class GiocatoreTest {
	
	static Giocatore giocatore;
	
	@BeforeClass
	public static void init() throws IOException{
		ArrayList<Emporio> empori = new ArrayList<>();
		for(int i=0; i< 7; i++){
			empori.add(new Emporio(new Colore("giallo")));
		}
		Mazzo<CartaPolitica> mazzo = Reader_new.letturaCartePolitica("src/test/resources/cartaPoliticaTest.txt");
		giocatore = new Giocatore(new Colore("giallo"), mazzo.getCarte(), new Aiutante(4), 3, 10, new PunteggioNobiltà(10, null), empori);
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


}
