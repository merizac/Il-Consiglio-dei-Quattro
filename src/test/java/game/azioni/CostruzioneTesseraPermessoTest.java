package game.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import bonus.Bonus;
import bonus.BonusAiutanti;
import bonus.BonusMoneta;
import game.Città;
import game.CittàBonus;
import game.GameState;
import game.Giocatore;
import game.TesseraPermesso;


public class CostruzioneTesseraPermessoTest {
	
	static GameState gameState;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState.start(giocatori);
	}
	
	@Test
	public void testEseguiAzione(){
		ArrayList<Bonus> bonus=new ArrayList<>();
		bonus.add(new BonusAiutanti(2));
		ArrayList<Città> città=new ArrayList<>();
		città.add((CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(0));
		TesseraPermesso tesseraPermesso=new TesseraPermesso(città, bonus, gameState.getRegioni().get(0));
		CostruzioneTesseraPermesso costruzioneTesseraPermesso=new CostruzioneTesseraPermesso();
		Città cittàCostruzione=tesseraPermesso.getCittà().get(0);
		costruzioneTesseraPermesso.setCittàCostruzione(cittàCostruzione);
		costruzioneTesseraPermesso.setTesseraPermessoScoperta(tesseraPermesso);
		
		costruzioneTesseraPermesso.eseguiAzione(gameState);

		//PUO PRENDERE ANCHE I BONUS DELLE CITTA VICINE
		int monete=0;
		int aiutanti=0;
		CittàBonus c=(CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(0);
		for(Bonus b: c.getBonus()){
			if (b instanceof BonusMoneta)
				monete=((BonusMoneta) b).getMonete();
			if(b instanceof BonusAiutanti)
				aiutanti= ((BonusAiutanti) b).getAiutanti();
		}
		
		//controllo che abbia costruito l'emporio
		assertTrue(gameState.getRegioni().get(0).getCittàRegione().get(0).getEmpori().contains(gameState.getGiocatoreCorrente().getEmpori().get(0)));
		//controllo che gli aiutanti siano corretti
		assertEquals(1+aiutanti, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
		//controllo che la tessera sia stata coperta
		assertTrue(gameState.getGiocatoreCorrente().getTesserePermessoUsate().contains(tesseraPermesso));	
	}
	
	@Test
	public void testSetandGetTesseraPermesso(){
		TesseraPermesso tesseraPermessoScoperta=gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(0);
		CostruzioneTesseraPermesso costruzioneTesseraPermesso=new CostruzioneTesseraPermesso();
		costruzioneTesseraPermesso.setTesseraPermessoScoperta(tesseraPermessoScoperta);
		assertTrue(tesseraPermessoScoperta==costruzioneTesseraPermesso.getTesseraPermessoScoperta());
		assertTrue(costruzioneTesseraPermesso.getTesseraPermessoScoperta() instanceof TesseraPermesso);
	}
	
	@Test
	public void testSetandGetCittàCostruzione(){
		Città cittàCostruzione=gameState.getRegioni().get(0).getCittàRegione().get(0);
		CostruzioneTesseraPermesso costruzioneTesseraPermesso=new CostruzioneTesseraPermesso();
		costruzioneTesseraPermesso.setCittàCostruzione(cittàCostruzione);
		assertTrue(cittàCostruzione==costruzioneTesseraPermesso.getCittàCostruzione());
		assertTrue(costruzioneTesseraPermesso.getCittàCostruzione() instanceof Città);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	TesseraPermesso tesseraPermesso;
	static GameState gameState;
	static Giocatore giocatoreCorrente;
	CostruzioneTesseraPermesso costruzioneTesseraPermesso = new CostruzioneTesseraPermesso();
	
	static ArrayList<Città> città = new ArrayList<>();
	static Colore colore = new Colore("C1");
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState.start(giocatori);

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
		gameState.getGiocatoreCorrente().aggiungiTesseraPermesso(new TesseraPermesso(città, null, regione));
		
	}
	
	@Test
	public void testEseguiAzione() {
		Regione regione = gameState.getRegioni().get(0);
		tesseraPermesso = regione.getTesserePermessoScoperte().get(0);
		
		int numEmpori = gameState.getGiocatoreCorrente().getEmpori().size();
		int numTessere = gameState.getGiocatoreCorrente().getTesserePermesso().size();
		TesseraPermesso tessera=gameState.getGiocatoreCorrente().getTesserePermesso().get(0);
		int numTessereUsate =gameState.getGiocatoreCorrente().getTesserePermessoUsate().size();
		
		costruzioneTesseraPermesso.setCittàCostruzione(città.get(0));
		costruzioneTesseraPermesso.setTesseraPermessoScoperta(new TesseraPermesso(città, null, regione));
		costruzioneTesseraPermesso.eseguiAzione(gameState);
		
		assertEquals(numEmpori-1, gameState.getGiocatoreCorrente().getEmpori().size());
		assertTrue(costruzioneTesseraPermesso.getCittàCostruzione().getEmpori().contains(new Emporio(colore)));
		System.out.println(giocatoreCorrente.getTesserePermesso().size());
		assertEquals(numTessere-1, giocatoreCorrente.getTesserePermesso().size());
		assertEquals(numTessereUsate+1, gameState.getGiocatoreCorrente().getTesserePermessoUsate().size());
		
	}*/

}
