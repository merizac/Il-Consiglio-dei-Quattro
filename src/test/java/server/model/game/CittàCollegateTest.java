package server.model.game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Set;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.CittàCollegate;
import server.model.game.Colore;
import server.model.game.Emporio;
import server.model.game.Mappa;
import server.model.game.Reader;

public class CittàCollegateTest {

	static Mappa mappa;
	static Città città=null;
	static Reader reader= new Reader();

	@BeforeClass
	public static void init() throws IOException{
		reader.letturaConsigliere();
		reader.letturaRegioni();
		mappa=reader.creazioneMappa("mappa1",reader.letturaColoriCittà("mappa1"));
		
		System.out.println("Ho letto la mappa");
		
		Emporio emporio=new Emporio(new Colore("C_E"));
		SimpleGraph<Città, DefaultEdge>grafo=mappa.getGrafo();
		for(Città c: grafo.vertexSet()){
			if (c.getNome().equals("Arkon"))
				c.aggiungiEmporio(emporio);
			if (c.getNome().equals("Castrum"))
				c.aggiungiEmporio(emporio);
			if (c.getNome().equals("Burgen")){
				c.aggiungiEmporio(emporio);
				città=c;
			}
			if (c.getNome().equals("Graden"))
				c.aggiungiEmporio(emporio);
			if (c.getNome().equals("Juvelar"))
				c.aggiungiEmporio(emporio);
		}
	}
	

	@Test
	public void testCittàBonusEmporio() {
		CittàCollegate cittàCollegate=new CittàCollegate();
		Set<CittàBonus> cittàC=cittàCollegate.cittàBonusEmporio(mappa.getGrafo(), new Colore("C_E"),città);
		for(Città c:cittàC){
			if(c.getNome().equals("Arkon"))
			assertEquals(c.getNome(), "Arkon");
			else if(c.getNome().equals("Castrum"))
			assertEquals(c.getNome(), "Castrum");
			else if(c.getNome().equals("Burgen"))
			assertEquals(c.getNome(), "Burgen");
			else
				fail("failed");
		}
		
	}
}
