package game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.cg17.Reader_new;
import junit.framework.AssertionFailedError;

public class CittàCollegateTest {

	static Mappa mappa;
	static Città città=null;
	static Reader_new reader= new Reader_new();

	@BeforeClass
	public static void init() throws IOException{
		reader.letturaConsigliere();
		reader.letturaRegioni();
		mappa=reader.creazioneMappa("src/main/resources/città.txt");
		
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
		HashSet<CittàBonus> cittàC=cittàCollegate.cittàBonusEmporio(mappa.getGrafo(), new Colore("C_E"),città);
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
