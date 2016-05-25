package game.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.Città;
import game.Colore;
import game.Emporio;
import game.GameState;
import game.Giocatore;
import game.Regione;
import game.TesseraPermesso;

public class CostruzioneTesseraPermessoTest {
	TesseraPermesso tesseraPermesso;
	static GameState gameState;
	static Giocatore giocatoreCorrente;
	CostruzioneTesseraPermesso costruzioneTesseraPermesso = new CostruzioneTesseraPermesso();
	
	static ArrayList<Città> città = new ArrayList<>();
	static Colore colore = new Colore("C1");
	
	@BeforeClass
	public static void init() throws IOException{
		gameState = new GameState();
		Regione regione = gameState.getRegioni().get(0);
		ArrayList<Emporio> empori =  new ArrayList<Emporio>();
		giocatoreCorrente = new Giocatore("nome");
		giocatoreCorrente.setColoreGiocatore(colore);
		giocatoreCorrente.aggiungiEmpori(empori);
		gameState.setGiocatoreCorrente(giocatoreCorrente);
		empori.add(new Emporio(colore));
		empori.add(new Emporio(colore));
		empori.add(new Emporio(colore));
		empori.add(new Emporio(colore));

		città.add(new Città("Juvelar", regione, null));
		giocatoreCorrente.aggiungiTesseraPermesso(new TesseraPermesso(città, null, regione));
		
	}
	
	@Test
	public void testEseguiAzione() {
		Regione regione = gameState.getRegioni().get(0);
		tesseraPermesso = regione.getTesserePermessoScoperte().get(0);
		
		
		int numEmpori = giocatoreCorrente.getEmpori().size();
		int numTessere = giocatoreCorrente.getTesserePermesso().size();
		int numTessereUsate =giocatoreCorrente.getTesserePermessoUsate().size();
		
		costruzioneTesseraPermesso.setCittàCostruzione(new Città("Juvelar", regione, null));
		costruzioneTesseraPermesso.setTesseraPermessoScoperta(new TesseraPermesso(città, null, regione));
		costruzioneTesseraPermesso.eseguiAzione(gameState);
		
		assertEquals(numEmpori-1, gameState.getGiocatoreCorrente().getEmpori().size());
		assertTrue(costruzioneTesseraPermesso.getCittàCostruzione().getEmpori().contains(new Emporio(colore)));
		System.out.println(giocatoreCorrente.getTesserePermesso().size());
		assertEquals(numTessere-1, giocatoreCorrente.getTesserePermesso().size());
		assertEquals(numTessereUsate+1, gameState.getGiocatoreCorrente().getTesserePermessoUsate().size());
		
	}

}
