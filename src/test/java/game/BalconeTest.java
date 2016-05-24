package game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.text.AbstractDocument.LeafElement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.query.ConsiglieriNotify;
import it.polimi.ingsw.cg17.Reader;

public class BalconeTest {

	static ArrayList<Consigliere> consiglieri=new ArrayList<Consigliere>();
	static Balcone balcone;
	
	@BeforeClass
	public static void initBalcone(){
		consiglieri.add(new Consigliere(new Colore("A")));
		consiglieri.add(new Consigliere(new Colore("B")));
		consiglieri.add(new Consigliere(new Colore("C")));
		consiglieri.add(new Consigliere(new Colore("D")));
		
		balcone= new Balcone(consiglieri);
	}
	
	
	@Before
	public void beforeTestBalcone() throws IOException {
		System.out.println("Balcone:" +balcone);
	}

	@After
	public void afterTestBalcone() throws IOException {
		System.out.println("Balcone:" +balcone);
	}

	
	@Test
	public void testAggiungiConsigliere() {
		Consigliere consigliere=new Consigliere(new Colore("E"));
		Consigliere consigliereRimosso=balcone.aggiungiConsigliere(consigliere);
		assertEquals(new Consigliere(new Colore("A")), consigliereRimosso);
	}
}
