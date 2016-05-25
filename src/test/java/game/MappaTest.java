package game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.cg17.Reader;

public class MappaTest {

	static HashSet<Città> città;
	static Mappa mappa;
	static Reader reader= new Reader();
	
	@BeforeClass
	public static void init() throws IOException{
	reader.letturaConsigliere();
	reader.letturaRegioni();
	città = new HashSet <Città> (reader.letturaCittà("mappa1"));
	mappa = new Mappa(città);
	}
	
	@Test
	public void testTrovaCittà() {
		Città cittàEmporio=null;
		Colore colore = new Colore("C1");
		for(Città c: città){
			if(c.getNome().equals("Arkon"))
				c.aggiungiEmporio(new Emporio(colore));
			if(c.getNome().equals("Burgen"))
				c.aggiungiEmporio(new Emporio(colore));
			if(c.getNome().equals("Esti"))
				c.aggiungiEmporio(new Emporio(colore));
			if(c.getNome().equals("Graden"))
				c.aggiungiEmporio(new Emporio(colore));
			if(c.getNome().equals("Osium"))
				c.aggiungiEmporio(new Emporio(colore));
			if(c.getNome().equals("Dorful"))
				cittàEmporio = c;

		}
		
		assertEquals(mappa.trovaCittà(cittàEmporio, colore).size(),5);
		
	}

	@Test
	public void testMinimaDistanza() {
		Città cittàPartenza=null;
		Città cittàDestinazione=null;
		
		for(Città c: città){
			if(c.getNome().equals("Burgen"))
				cittàPartenza=c;
			if(c.getNome().equals("Osium"))
				cittàDestinazione=c;
		}
		int minimaDistanza = mappa.minimaDistanza(cittàPartenza, cittàDestinazione);
		assertEquals(minimaDistanza, 4);
		
	}

}



