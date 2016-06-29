package common.gameDTO;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.BonusGettoneNDTO;
import server.model.game.GameState;
import server.model.game.Giocatore;

public class GiocatoreDTOTest {

	static GameState gameState;
	static Giocatore giocatore;
	static GiocatoreDTO giocatoreDTO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatore = new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState = new GameState();
		gameState.start(giocatori, "mappa1");
		giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
	}

	@Test
	public void testGiocatoreDTO() {
		GiocatoreDTO giocatore = new GiocatoreDTO();
		assertTrue(giocatore.getCartePolitica() != null);
		assertTrue(giocatore.getTesserePermessoUsate() != null);
		assertTrue(giocatore.getTesserePermesso() != null);
	}

	@Test
	public void testGetNome() {
		giocatoreDTO.setNome("G");
		assertEquals("G", giocatoreDTO.getNome());
	}

	@Test
	public void testSetNome() {
		giocatoreDTO.setNome("G");
		assertEquals("G", giocatoreDTO.getNome());
	}

	@Test
	public void testGetColoreGiocatore() {
		ColoreDTO colore = new ColoreDTO();
		colore.setColore("C");
		giocatoreDTO.setColoreGiocatore(colore);
		assertEquals(colore, giocatoreDTO.getColoreGiocatore());
	}

	@Test
	public void testSetColoreGiocatore() {
		ColoreDTO colore = new ColoreDTO();
		colore.setColore("C");
		giocatoreDTO.setColoreGiocatore(colore);
		assertEquals(colore, giocatoreDTO.getColoreGiocatore());
	}

	@Test
	public void testGetCartePolitica() {
		List<CartaPoliticaDTO> cartePolitica = new ArrayList<>();
		CartaPoliticaDTO carta = new CartaPoliticaDTO();
		carta.setColore("C");
		cartePolitica.add(carta);
		giocatoreDTO.setCartePolitica(cartePolitica);
		assertEquals(cartePolitica, giocatoreDTO.getCartePolitica());
	}

	@Test
	public void testSetCartePolitica() {
		List<CartaPoliticaDTO> cartePolitica = new ArrayList<>();
		CartaPoliticaDTO carta = new CartaPoliticaDTO();
		carta.setColore("C");
		cartePolitica.add(carta);
		giocatoreDTO.setCartePolitica(cartePolitica);
		assertTrue(cartePolitica == giocatoreDTO.getCartePolitica());
	}

	@Test
	public void testGetTesserePermesso() {
		List<TesseraPermessoDTO> tessere = new ArrayList<>();
		TesseraPermessoDTO tessera = new TesseraPermessoDTO();
		tessera.inizializza(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(0));
		giocatoreDTO.setTesserePermesso(tessere);
		assertTrue(tessere == giocatoreDTO.getTesserePermesso());
	}

	@Test
	public void testSetTesserePermesso() {
		List<TesseraPermessoDTO> tessere = new ArrayList<>();
		TesseraPermessoDTO tessera = new TesseraPermessoDTO();
		tessera.inizializza(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(0));
		giocatoreDTO.setTesserePermesso(tessere);
		assertTrue(tessere == giocatoreDTO.getTesserePermesso());
	}

	@Test
	public void testGetTesserePermessoUsate() {
		List<TesseraPermessoDTO> tessere = new ArrayList<>();
		TesseraPermessoDTO tessera = new TesseraPermessoDTO();
		tessera.inizializza(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(0));
		giocatoreDTO.setTesserePermessoUsate(tessere);
		assertTrue(tessere == giocatoreDTO.getTesserePermessoUsate());
	}

	@Test
	public void testSetTesserePermessoUsate() {
		List<TesseraPermessoDTO> tessere = new ArrayList<>();
		TesseraPermessoDTO tessera = new TesseraPermessoDTO();
		tessera.inizializza(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(0));
		giocatoreDTO.setTesserePermessoUsate(tessere);
		assertTrue(tessere == giocatoreDTO.getTesserePermessoUsate());
	}

	@Test
	public void testGetTessereBonus() {
		giocatoreDTO.setTessereBonus(10);
		assertEquals(10, giocatoreDTO.getTessereBonus());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetTessereBonusNegativo() {
		giocatoreDTO.setTessereBonus(-1);
	}

	@Test
	public void testSetTessereBonus() {
		giocatoreDTO.setTessereBonus(10);
		assertEquals(10, giocatoreDTO.getTessereBonus());
	}

	@Test
	public void testGetEmpori() {
		giocatoreDTO.setEmpori(2);
		assertEquals(2, giocatoreDTO.getEmpori());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetEmporiNegativo() {
		giocatoreDTO.setEmpori(-1);
	}

	@Test
	public void testSetEmpori() {
		giocatoreDTO.setEmpori(2);
		assertEquals(2, giocatoreDTO.getEmpori());
	}

	@Test
	public void testGetAiutanti() {
		giocatoreDTO.setAiutanti(2);
		assertEquals(2, giocatoreDTO.getAiutanti());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetAiutantiNegativo() {
		giocatoreDTO.setAiutanti(-2);
	}

	@Test
	public void testSetAiutanti() {
		giocatoreDTO.setAiutanti(2);
		assertEquals(2, giocatoreDTO.getAiutanti());
	}

	@Test
	public void testGetPunteggioVittoria() {
		giocatoreDTO.setPunteggioVittoria(2);
		assertEquals(2, giocatoreDTO.getPunteggioVittoria());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPunteggioVittoria() {
		giocatoreDTO.setAiutanti(-2);
	}

	@Test
	public void testGetPunteggioRicchezza() {
		giocatoreDTO.setPunteggioRicchezza(10);
		assertEquals(10, giocatoreDTO.getPunteggioRicchezza());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setPunteggioNobiltà() {
		giocatoreDTO.setPunteggioNobiltà(-5);
	}

	@Test
	public void testGetPunteggioNobiltà() {
		giocatoreDTO.setPunteggioNobiltà(2);
		assertEquals(2, giocatoreDTO.getPunteggioNobiltà());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPunteggioNobiltà() {
		giocatoreDTO.setPunteggioNobiltà(-1);
	}

	@Test
	public void testInizializza() {
		GiocatoreDTO g = new GiocatoreDTO();
		g.inizializza(giocatore);

		assertEquals(giocatore.getNome(), g.getNome());
		assertEquals(giocatore.getColoreGiocatore().getColore(), g.getColoreGiocatore().getColore());
		assertEquals(giocatore.getCartePolitica().size(), g.getCartePolitica().size());
		assertEquals(giocatore.getNumeroTesserePermesso(),
				g.getTesserePermesso().size() + g.getTesserePermessoUsate().size());
		assertEquals(0, g.getTessereBonus());
		assertEquals(giocatore.getPunteggioNobiltà().getPuntiNobiltà(), g.getPunteggioNobiltà());
		assertEquals(giocatore.getPunteggioRicchezza(), g.getPunteggioRicchezza());
		assertEquals(giocatore.getPunteggioVittoria(), g.getPunteggioVittoria());
		assertEquals(giocatore.getTesserePermessoUsate().size(), g.getTesserePermessoUsate().size());
		assertEquals(giocatore.getTesserePermesso().size(), g.getTesserePermesso().size());
	}

	@Test
	public void testToString() {
		assertEquals("\n" + giocatoreDTO.getNome() + " [ " + giocatoreDTO.getColoreGiocatore() + " ]\ncartePolitica = "
				+ giocatoreDTO.getCartePolitica() + "\ntesserePermesso = " + giocatoreDTO.getTesserePermesso()
				+ "\ntesserePermessoUsate = " + giocatoreDTO.getTesserePermessoUsate() + "\ntessereBonus="
				+ giocatoreDTO.getTessereBonus() + "\nempori = " + giocatoreDTO.getEmpori() + "\naiutanti = "
				+ giocatoreDTO.getAiutanti() + "\npunteggioVittoria = " + giocatoreDTO.getPunteggioVittoria()
				+ "\npunteggioRicchezza = " + giocatoreDTO.getPunteggioRicchezza() + "\npunteggioNobiltà = "
				+ giocatoreDTO.getPunteggioNobiltà(), giocatoreDTO.toString());
	}

	@Test
	public void testGetBonusNobiltà() {
		List<AzioneDTO> bonus = new ArrayList<>();
		bonus.add(new BonusGettoneNDTO());
		giocatoreDTO.setBonusNobiltà(bonus);

		assertTrue(bonus == giocatoreDTO.getBonusNobiltà());
	}

	@Test(expected = NullPointerException.class)
	public void testSetBonusNobiltà() {
		giocatoreDTO.setBonusNobiltà(null);
	}

	@Test
	public void testEqualsObject() {
		GiocatoreDTO g1=new GiocatoreDTO();
		g1.inizializza(giocatore);
		GiocatoreDTO g2=new GiocatoreDTO();
		g2.inizializza(giocatore);
		assertEquals(g1, g2);
	}

}
