package server.model.game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.model.bonus.BonusPuntiVittoria;
import server.model.game.Città;
import server.model.game.ColoreRe;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class ReTest {
	
	static GameState gameState;
	ArrayList<Città> città;
	static Città nuovaCittà;
	static ArrayList<Giocatore> giocatori;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		Giocatore giocatore=new Giocatore("Pippo");
		giocatori=new ArrayList<Giocatore>();
		giocatori.add(giocatore);
		gameState.start(giocatori, "mappa1");
//		ArrayList<Consigliere> consiglieri=new ArrayList<Consigliere>();
//		città=new Città("cittàRe", new Regione("regione", new Mazzo<TesseraPermesso>(), new BonusPuntiVittoria(2), new Balcone(consiglieri)), new Colore("coloreCittàRe"));
//		re=new Re(città);
	}
	
	@Before
	public void beforeTest(){
		città=new ArrayList<Città>(gameState.getCittà());
		nuovaCittà=città.get(0);

	}
	
	@Test
	public void testGetCittà() {
		Città cittàRe=null;
		
		for(Città c:città){
			if(c.getColoreCittà() instanceof ColoreRe)
				cittàRe=c;
		}
		assertEquals(gameState.getPedinaRe().getCittà(), cittàRe);
	}

	@Test
	public void testSetCittà() {
		gameState.getPedinaRe().setCittà(nuovaCittà);
		assertEquals(gameState.getPedinaRe().getCittà(), nuovaCittà);

	}

}
