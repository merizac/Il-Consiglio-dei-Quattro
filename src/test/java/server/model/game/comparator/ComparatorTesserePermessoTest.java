package server.model.game.comparator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ComparatorTesserePermessoTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(new Giocatore("G1"));
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
	}
		
	@Test
	public void vinceIlPrimoGiocatorePassato() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
						
		//assegno loro un certo numero di tessere permesso
		for(int i=0;i<2;i++){
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for(int i=0;i<1;i++){
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorTesserePermesso comparator=new ComparatorTesserePermesso();
		assertEquals(-1,comparator.compare(g1,g2));
	}

	@Test
	public void vinceIlSecondoGiocatorePassato() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
						
		//assegno loro un certo numero di tessere permesso
		for(int i=0;i<0;i++){
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for(int i=0;i<1;i++){
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorTesserePermesso comparator=new ComparatorTesserePermesso();
		assertEquals(1,comparator.compare(g1,g2));
	}

	@Test
	public void pareggio() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
						
		//assegno loro un certo numero di tessere permesso
		for(int i=0;i<1;i++){
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for(int i=0;i<1;i++){
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorTesserePermesso comparator=new ComparatorTesserePermesso();
		assertEquals(0,comparator.compare(g1,g2));
	}

}
