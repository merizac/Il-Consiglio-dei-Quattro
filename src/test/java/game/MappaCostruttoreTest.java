package game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.cg17.Reader;

public class MappaCostruttoreTest {
	static HashSet<Città> città;
	static Mappa mappa;
	static Reader reader= new Reader();
	
	@BeforeClass
	public static void init() throws IOException{
	reader.letturaConsigliere();
	reader.letturaRegioni();
	città = new HashSet <Città> (reader.letturaCittà("src/main/resources/città.txt"));
	//System.out.println(città);
	}
			
	@Test
	public void controlloCostruzioneVertici() {
		mappa = new Mappa(città);
		for(Città c: città){
			assertTrue(mappa.getGrafo().containsVertex(c));
		}
	}
	
}

	