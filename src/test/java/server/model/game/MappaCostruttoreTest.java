package server.model.game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.Città;
import server.model.game.Mappa;
import server.model.game.Reader;

public class MappaCostruttoreTest {
	static HashSet<Città> città;
	static Mappa mappa;
	static Reader reader= new Reader();
	
	@BeforeClass
	public static void init() throws IOException{
	reader.letturaConsigliere();
	reader.letturaRegioni();
	città = new HashSet <Città> (reader.letturaCittà("mappa1",reader.letturaColoriCittà("mappa1")));
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

	