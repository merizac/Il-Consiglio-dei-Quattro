package common.azioniDTO;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.BonusGettoneNDTO;
import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàBonusDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.Azione;
import server.model.azioni.azioniBonus.BonusGettoneN;
import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import server.model.bonus.Bonus;
import server.model.bonus.BonusPuntiNobiltà;
import server.model.game.CartaPolitica;
import server.model.game.CittàBonus;
import server.model.game.Colore;
import server.model.game.GameState;
import server.model.game.Giocatore;
import utility.ParameterException;

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
	public void testAccept() throws ParameterException {
		ArrayList<CittàBonus> c=new ArrayList<>();
		CittàBonus city=(CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(0);
		int i=1;
		for(Bonus b:city.getBonus()){
			if(b instanceof BonusPuntiNobiltà){
				city=(CittàBonus) gameState.getRegioni().get(0).getCittàRegione().get(i);
				i++;
			}
			else{
				c.add(city);
				break;
			}
		}
		
		bonusGettoneNDTO.setNumeroGettoni(1);
		bonusGettoneNDTO.setCittà(città);
		
		BonusGettoneN azioneParser=(BonusGettoneN) bonusGettoneNDTO.accept(visitor);
		
		assertEquals(1, azioneParser.getNumeroGettoni());
		assertTrue(azioneParser.getCittà().equals(c));
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

	@Test
	public void testToStringUnGettone(){
		BonusGettoneNDTO bonus=new BonusGettoneNDTO();
		bonus.setNumeroGettoni(1);
		assertEquals("Bonus Gettone [B3]", bonus.toString());
	}

	
}
