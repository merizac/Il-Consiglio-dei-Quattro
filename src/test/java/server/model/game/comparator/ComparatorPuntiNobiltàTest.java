package server.model.game.comparator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.PunteggioNobiltà;

public class ComparatorPuntiNobiltàTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(new Giocatore("G1"));
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
	}

	@Test
	public void vinceGiocatore1() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro il punteggio nobiltà
		for(PunteggioNobiltà p:gameState.getPlanciaRe().getPercorsoNobiltà()){
			if(p.getPuntiNobiltà()==2)
				g1.setPunteggioNobiltà(p);
			if(p.getPuntiNobiltà()==1)
				g2.setPunteggioNobiltà(p);
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorPuntiNobiltà comparator=new ComparatorPuntiNobiltà();
		assertEquals(-1,comparator.compare(g1,g2));
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
		
		//assegno loro il punteggio nobiltà
		for(PunteggioNobiltà p:gameState.getPlanciaRe().getPercorsoNobiltà()){
			if(p.getPuntiNobiltà()==1)
				g1.setPunteggioNobiltà(p);
			if(p.getPuntiNobiltà()==1)
				g2.setPunteggioNobiltà(p);
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorPuntiNobiltà comparator=new ComparatorPuntiNobiltà();
		assertEquals(0,comparator.compare(g1,g2));
	}

	@Test
	public void vinceGiocatore2() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro il punteggio nobiltà
		for(PunteggioNobiltà p:gameState.getPlanciaRe().getPercorsoNobiltà()){
			if(p.getPuntiNobiltà()==1)
				g1.setPunteggioNobiltà(p);
			if(p.getPuntiNobiltà()==2)
				g2.setPunteggioNobiltà(p);
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorPuntiNobiltà comparator=new ComparatorPuntiNobiltà();
		assertEquals(1,comparator.compare(g1,g2));
	}

}
