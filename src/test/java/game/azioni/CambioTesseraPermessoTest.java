package game.azioni;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import game.GameState;
import game.Regione;
import game.TesseraPermesso;

public class CambioTesseraPermessoTest {

	Regione regione;
	GameState gameState;
	CambioTesseraPermesso cambioTesseraPermesso=new CambioTesseraPermesso();
	
	@Before
	public void init() throws IOException{
		gameState=new GameState();
	}
		
	@Test
	public void testEseguiAzione() {
		regione=gameState.getRegioni().get(0);
		TesseraPermesso t1=regione.getTesserePermessoScoperte().get(0);
		TesseraPermesso t2=regione.getTesserePermessoScoperte().get(1);
		cambioTesseraPermesso.setRegione(regione);
		cambioTesseraPermesso.eseguiAzione(gameState);
		assertNotNull(regione.getTesserePermessoScoperte().get(0));
		assertNotNull(regione.getTesserePermessoScoperte().get(1));
		assertNotEquals(t1,regione.getTesserePermessoScoperte().get(0));
		assertNotEquals(t2,regione.getTesserePermessoScoperte().get(1));
		
	}

}
