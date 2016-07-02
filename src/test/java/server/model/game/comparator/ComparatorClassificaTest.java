package server.model.game.comparator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Aiutante;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ComparatorClassificaTest {

	static GameState gameState;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(new Giocatore("G1"));
		gameState=new GameState();
		gameState.start(giocatori, "mappa1");
	}
	
/*
-1 vince il primo
0 paraggiano
1 vince il secondo

Legenda Test: casi possibili caso_vittoria_aiutanti_cartepolitica 1-> g1>g2 0-> g1=g2
vittoria		11110000
aiutanti		11001100
cartepolitica	10101010
*/
	
	@Test
	public void caso111() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(20);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<3;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(-1,comparator.compare(g1,g2));
	}

	@Test
	public void caso110() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(20);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<2;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(-1,comparator.compare(g1,g2));
	}

	@Test
	public void caso101() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(20);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<3;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(-1,comparator.compare(g1,g2));
	}

	@Test
	public void caso100() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(20);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<2;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(-1,comparator.compare(g1,g2));
	}

	@Test
	public void caso011() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(10);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(3));
		g2.setAiutanti(new Aiutante(2));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<3;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(-1,comparator.compare(g1,g2));
	}

	@Test
	public void caso010() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(10);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<2;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(-1,comparator.compare(g1,g2));
	}
	
	@Test
	public void caso001() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(10);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<3;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(-1,comparator.compare(g1,g2));
	}

	@Test
	public void caso000() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(10);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<2;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(0,comparator.compare(g1,g2));
	}
	
	@Test
	public void caso111r() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(20);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<3;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(1,comparator.compare(g2,g1));
	}

	@Test
	public void caso110r() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(20);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<2;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(1,comparator.compare(g2,g1));
	}

	@Test
	public void caso101r() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(20);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<3;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(1,comparator.compare(g2,g1));
	}

	@Test
	public void caso100r() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(20);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<2;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(1,comparator.compare(g2,g1));
	}

	@Test
	public void caso011r() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(10);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(3));
		g2.setAiutanti(new Aiutante(2));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<3;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(1,comparator.compare(g2,g1));
	}

	@Test
	public void caso010r() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(10);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<2;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(1,comparator.compare(g2,g1));
	}
	
	@Test
	public void caso001r() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(10);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<3;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(1,comparator.compare(g2,g1));
	}

	@Test
	public void caso000r() {
		gameState.getGiocatori().clear();

		//creo 2 nuovi giocatori
		Giocatore g1=new Giocatore("G1");
		Giocatore g2=new Giocatore("G2");
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		
		//assegno loro punti vittoria
		g1.getTessereBonus().clear();
		g1.setPunteggioVittoria(10);
		g2.getTessereBonus().clear();
		g2.setPunteggioVittoria(10);
		
		//assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
						
		//assegno loro un certo numero di carte politica
		for(int i=0;i<2;i++){
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for(int i=0;i<2;i++){
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
				
		//aggiungo ai giocatori fine partita
		gameState.getGiocatori().addAll(giocatori);

		ComparatorClassifica comparator=new ComparatorClassifica();
		assertEquals(0,comparator.compare(g2,g1));
	}
	
}
