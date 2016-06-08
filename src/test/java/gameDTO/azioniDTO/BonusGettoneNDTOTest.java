package gameDTO.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import game.CartaPolitica;
import game.CittàBonus;
import game.Colore;
import game.GameState;
import game.Giocatore;
import game.azioni.AcquistoTesseraPermesso;
import game.azioni.Azione;
import game.azioni.BonusGettoneN;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.CittàBonusDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class BonusGettoneNDTOTest {

	static GameState gameState;
	static AzioneVisitor visitor;
	static List<CittàBonusDTO> città;
	static int numeroGettoni;
	static BonusGettoneNDTO bonusGettoneNDTO;
	
	@BeforeClass
	public static void setUp() throws IOException{
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState=new GameState();
		gameState.start(giocatori);
		
		città=new ArrayList<>();
		CittàBonusDTO c=new CittàBonusDTO();
		c.inizializza((CittàBonus)gameState.getRegioni().get(0).getCittàRegione().get(0));
		città.add(c);
		bonusGettoneNDTO=new BonusGettoneNDTO();

		visitor = new AzioneVisitorImpl(gameState, gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetCittà() {
		bonusGettoneNDTO.setCittà(città);
		assertTrue(città==bonusGettoneNDTO.getCittà());
	}

	@Test
	public void testSetCittà() {
		bonusGettoneNDTO.setCittà(città);
		assertTrue(città==bonusGettoneNDTO.getCittà());
	}

	@Test
	public void testAccept() {
		ArrayList<CittàBonus> c=new ArrayList<>();
		c.add((CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(0));
		
		bonusGettoneNDTO.setNumeroGettoni(1);
		bonusGettoneNDTO.setCittà(città);
		System.out.println(città);
		
		BonusGettoneN azioneParser=(BonusGettoneN) bonusGettoneNDTO.accept(visitor);
		System.out.println();
		
		assertEquals(1, azioneParser.getNumeroGettoni());

	}

	@Test
	public void testGetNumeroGettoni() {
		bonusGettoneNDTO.setNumeroGettoni(1);
		assertTrue(1==bonusGettoneNDTO.getNumeroGettoni());
	}

	@Test
	public void testSetNumeroGettoni() {
		bonusGettoneNDTO.setNumeroGettoni(2);
		assertTrue(2==bonusGettoneNDTO.getNumeroGettoni());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNumeroGettoniNumeroMinoreUgualeAZero() {
		bonusGettoneNDTO.setNumeroGettoni(-2);
	}

}
