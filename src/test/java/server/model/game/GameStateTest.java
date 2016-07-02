package server.model.game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.bonus.BonusPuntiVittoria;
import server.model.macchinaStati.StartEnd;
import server.model.macchinaStati.Stato11;
import server.model.market.Offerta;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.Notify;
import utility.Observer;

public class GameStateTest {

	static GameState gameState;
	static Giocatore giocatore1;
	static Giocatore giocatore2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatore1 = new Giocatore("Giocatore1");
		giocatore2 = new Giocatore("Giocatore2");

		giocatori.add(giocatore1);
		giocatori.add(giocatore2);

		gameState = new GameState();
		gameState.start(giocatori, "mappa1");
	}

	@Test
	public void testGetConsigliere() {
		Consigliere consigliereAspettato = new Consigliere(new Colore("A"));
		gameState.getConsiglieri().add(consigliereAspettato);

		assertTrue(consigliereAspettato == gameState.getConsigliere("A"));
	}

	@Test
	public void testGetGiocatoreCorrente() {
		gameState.setGiocatoreCorrente(giocatore1);

		assertTrue(giocatore1 == gameState.getGiocatoreCorrente());
	}

	@Test
	public void testSetGiocatoreCorrente() {
		gameState.setGiocatoreCorrente(giocatore2);

		assertTrue(giocatore2 == gameState.getGiocatoreCorrente());
	}

	@Test
	public void testGetOfferteMarket() {
		Offerta offerta = new Offerta(giocatore1, new Aiutante(1), 2);
		gameState.getOfferteMarket().add(offerta);
		List<Offerta> offerte = new ArrayList<>();
		offerte.add(offerta);

		assertTrue(offerte.equals(gameState.getOfferteMarket()));
	}

	@Test
	public void testSetGiocatori() {
		List<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(giocatore1);

		gameState.setGiocatori(giocatori);

		assertTrue(giocatori.equals(gameState.getGiocatori()));
	}

	@Test
	public void testGetMappa() {
		assertTrue(gameState.getMappa() instanceof Mappa);
	}

	@Test
	public void testGetRegioni() {
		assertTrue(gameState.getRegioni().get(0) instanceof Regione);
	}

	@Test
	public void testGetPlanciaRe() {
		assertTrue(gameState.getPlanciaRe() instanceof PlanciaRe);
	}

	@Test
	public void testGetPedinaRe() {
		assertTrue(gameState.getPedinaRe() instanceof Re);
	}

	@Test
	public void testGetConsiglieri() {
		assertTrue(gameState.getConsiglieri().get(0) instanceof Consigliere);
	}

	@Test
	public void testGetMazzoCartePolitica() {
		assertTrue(gameState.getMazzoCartePolitica().getCarte().get(0) instanceof CartaPolitica);
	}

	@Test
	public void testGetCittà() {
		assertTrue(gameState.getCittà() == gameState.getMappa().getGrafo().vertexSet());
	}

	@Test
	public void testGetGiocatori() {
		List<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(giocatore1);

		gameState.setGiocatori(giocatori);

		assertTrue(giocatori.equals(gameState.getGiocatori()));
	}

	@Test
	public void testGetStato() {
		Stato11 stato11 = new Stato11(gameState);
		gameState.setStato(stato11);

		assertTrue(stato11 == gameState.getStato());
	}

	@Test
	public void testSetStato() {
		Stato11 stato11 = new Stato11(gameState);
		gameState.setStato(stato11);

		assertTrue(stato11 == gameState.getStato());
	}

	@Test
	public void testIsBonusAzionePrincipale() {
		gameState.setBonusAzionePrincipale(false);

		assertTrue(!gameState.isBonusAzionePrincipale());
	}

	@Test
	public void testSetBonusAzionePrincipale() {
		gameState.setBonusAzionePrincipale(true);

		assertTrue(gameState.isBonusAzionePrincipale());
	}

	@Test
	public void testGetNumeroTurni() {
		gameState.setNumeroTurni(3);

		assertTrue(3 == gameState.getNumeroTurni());
	}

	@Test
	public void testSetNumeroTurni() {
		gameState.setNumeroTurni(2);

		assertTrue(2 == gameState.getNumeroTurni());
	}

	@Test
	public void testIsUltimoGiro() {
		gameState.setUltimoGiro(true);

		assertTrue(gameState.isUltimoGiro());
	}

	@Test
	public void testSetUltimoGiro() {
		gameState.setUltimoGiro(false);

		assertTrue(!gameState.isUltimoGiro());
	}

	@Test
	public void testGetGiocatoriFinePartita() {
		List<Giocatore> giocatoriFinePartita = new ArrayList<>();
		gameState.setGiocatoriFinePartita(giocatoriFinePartita);

		assertTrue(giocatoriFinePartita == gameState.getGiocatoriFinePartita());
	}

	@Test
	public void testSetGiocatoriFinePartita() {
		List<Giocatore> giocatoriFinePartita = new ArrayList<>();
		gameState.setGiocatoriFinePartita(giocatoriFinePartita);

		assertTrue(giocatoriFinePartita == gameState.getGiocatoriFinePartita());
	}

	@Test
	public void testProssimoTurno() {
		int turno = gameState.getNumeroTurni();
		gameState.prossimoTurno();

		assertTrue(turno + 1 == gameState.getNumeroTurni());
	}

/*	@Test public void testCreaGiocatoriUGUALE2() {
		gameState.getGiocatori().clear();
		List<Giocatore> giocatori = new ArrayList<>();
		Giocatore gamer1 = new Giocatore("Giocatore1");
		Giocatore gamer2 = new Giocatore("Giocatore2");
		giocatori.add(gamer1);
		giocatori.add(gamer2);

		gameState.creaGiocatori(giocatori);

		Giocatore g1 = gameState.getGiocatori().get(0);
		Giocatore g2 = gameState.getGiocatori().get(1);

		assertTrue(gamer1 == gameState.getGiocatoreCorrente());
		assertTrue("Giocatore1" == g1.getNome());
		assertTrue("Giocatore2" == g2.getNome());
		assertTrue(6 == g1.getCartePolitica().size());
		assertTrue(1 == g1.getAiutanti().getAiutante());
		assertTrue(0 == g1.getPunteggioNobiltà().getPuntiNobiltà());
		assertTrue(10 == g1.getPunteggioRicchezza());
		assertTrue("0".equals(g1.getColoreGiocatore().getColore()));
		assertTrue("0".equals(g1.getEmpori().get(0).getColore().getColore()));
		assertTrue(10 == g1.getEmpori().size());
		assertTrue(6 == g1.getCartePolitica().size());
		assertTrue(gameState.getGiocatori().contains(g1));

		assertTrue(6 == g2.getCartePolitica().size());
		assertTrue(2 == g2.getAiutanti().getAiutante());
		assertTrue(0 == g2.getPunteggioNobiltà().getPuntiNobiltà());
		assertTrue(11 == g2.getPunteggioRicchezza());
		assertTrue("1".equals(g2.getColoreGiocatore().getColore()));
		assertTrue("1".equals(g2.getEmpori().get(0).getColore().getColore()));
		assertTrue(10 == g2.getEmpori().size());
		assertTrue(6 == g2.getCartePolitica().size());
		assertTrue(gameState.getGiocatori().contains(g2));
	}

	@Test
	public void testCreaGiocatoriDIVERSO2() {
		gameState.getGiocatori().clear();
		List<Giocatore> giocatori = new ArrayList<>();
		Giocatore gamer1 = new Giocatore("G1");
		Giocatore gamer2 = new Giocatore("G2");
		Giocatore gamer3 = new Giocatore("G3");
		giocatori.add(gamer1);
		giocatori.add(gamer2);
		giocatori.add(gamer3);

		gameState.creaGiocatori(giocatori);

		Giocatore g1 = gameState.getGiocatori().get(0);
		Giocatore g2 = gameState.getGiocatori().get(1);
		Giocatore g3 = gameState.getGiocatori().get(2);

		assertTrue(gamer1 == gameState.getGiocatoreCorrente());
		assertTrue("G1" == g1.getNome());
		assertTrue("G2" == g2.getNome());
		assertTrue(6 == g1.getCartePolitica().size());
		assertTrue(1 == g1.getAiutanti().getAiutante());
		assertTrue(0 == g1.getPunteggioNobiltà().getPuntiNobiltà());
		assertTrue(10 == g1.getPunteggioRicchezza());
		assertTrue("0".equals(g1.getColoreGiocatore().getColore()));
		assertTrue("0".equals(g1.getEmpori().get(0).getColore().getColore()));
		assertTrue(10 == g1.getEmpori().size());
		assertTrue(6 == g1.getCartePolitica().size());
		assertTrue(gameState.getGiocatori().contains(g1));

		assertTrue(6 == g2.getCartePolitica().size());
		assertTrue(2 == g2.getAiutanti().getAiutante());
		assertTrue(0 == g2.getPunteggioNobiltà().getPuntiNobiltà());
		assertTrue(11 == g2.getPunteggioRicchezza());
		assertTrue("1".equals(g2.getColoreGiocatore().getColore()));
		assertTrue("1".equals(g2.getEmpori().get(0).getColore().getColore()));
		assertTrue(10 == g2.getEmpori().size());
		assertTrue(6 == g2.getCartePolitica().size());
		assertTrue(gameState.getGiocatori().contains(g2));
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Test
	public void testNextPlayer() {
		Giocatore fineTurno = gameState.getGiocatori().get(0);
		Giocatore nuovoTurno = gameState.getGiocatori().get(1);

		gameState.nextPlayer();

		assertTrue(gameState.getGiocatori().get(gameState.getGiocatori().size() - 1) == fineTurno);
		assertTrue(nuovoTurno == gameState.getGiocatoreCorrente());
	}

	@Test
	public void testLastNextPlayerAConAncoraGiocatori() {
		gameState.getGiocatori().clear();
		List<Giocatore> giocatori = new ArrayList<>();
		Giocatore gamer1 = new Giocatore("G1");
		Giocatore gamer2 = new Giocatore("G2");
		giocatori.add(gamer1);
		giocatori.add(gamer2);
		gameState.getGiocatori().addAll(giocatori);

		gameState.setGiocatoreCorrente(gamer1);

		assertTrue(!gameState.lastNextPlayer());
		assertTrue(gameState.getGiocatoreCorrente() == gamer2);
	}

	@Test
	public void testLastNextPlayerGiocatoriFiniti() {
		gameState.getGiocatori().clear();
		List<Giocatore> giocatori = new ArrayList<>();
		Giocatore gamer1 = new Giocatore("G1");
		giocatori.add(gamer1);
		gameState.getGiocatori().addAll(giocatori);

		gameState.setGiocatoreCorrente(gamer1);

		assertTrue(gameState.lastNextPlayer());
	}

	@Test
	public void testDecrementaTurno() {
		int turno = gameState.getNumeroTurni();
		gameState.decrementaTurno();

		assertTrue(turno - 1 == gameState.getNumeroTurni());

	}

	@Test
	public void testStart() throws IOException {
		Reader reader = new Reader();
		List<Notify> notify = new ArrayList<>();
		Observer<Notify> observer = new Observer<Notify>() {

			@Override
			public void update(Notify c) {
				notify.add(c);
			}
		};

		GameState game = new GameState();
		game.registerObserver(observer);

		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatore1 = new Giocatore("Giocatore1");
		giocatori.add(giocatore1);

		game.start(giocatori,"mappa1");

		assertTrue(notify.get(0) instanceof GameStateNotify);
		assertTrue(notify.get(1) instanceof GiocatoreNotify);
		assertTrue(game.getStato() instanceof StartEnd);
		assertTrue(reader.letturaConsigliere().containsAll(game.getConsiglieri()));
		assertTrue(reader.letturaRegioni().containsAll(game.getRegioni()));
		assertTrue(reader.creazionePlanciaRe().getBonusPremioRe().equals(game.getPlanciaRe().getBonusPremioRe()));
		assertTrue(reader.creazionePlanciaRe().getPercorsoNobiltà().equals(game.getPlanciaRe().getPercorsoNobiltà()));
		assertTrue(reader.letturaCartePolitica().getCarte().containsAll(game.getMazzoCartePolitica().getCarte()));

	}

	@Test
	public void G1VinceSuTutto() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(20);
		g2.setPunteggioVittoria(1);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 0)
				g2.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di carte politica
		for (int i = 0; i < 2; i++) {
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 2; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}

		// assegno loro tessereBonus(punti vittoria)
		g1.getTessereBonus().clear();
		g1.getTessereBonus().add(new BonusPuntiVittoria(10));
		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(2));

		List<Giocatore> vincitori = gameState.calcolaVincitore();
		assertTrue(g1 == vincitori.get(0));
		assertTrue(vincitori.size() == 1);
		assertTrue(20 + 10 + 5 + 3 == vincitori.get(0).getPunteggioVittoria());
		assertTrue(2 + 1 + 2 == gameState.getGiocatoriFinePartita().get(1).getPunteggioVittoria());
	}

	@Test
	public void G1VinceConPariPuntiVittoria() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(1);
		g2.setPunteggioVittoria(1);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 0)
				g2.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di carte politica
		for (int i = 0; i < 2; i++) {
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 2; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}

		// assegno loro tessereBonus(punti vittoria)
		g1.getTessereBonus().clear();
		g1.getTessereBonus().add(new BonusPuntiVittoria(2));
		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(2));

		List<Giocatore> vincitori = gameState.calcolaVincitore();
		assertTrue(g1 == vincitori.get(0));
		assertTrue(vincitori.size() == 1);
		assertTrue(1 + 2 + 5 + 3 == vincitori.get(0).getPunteggioVittoria());
		assertTrue(2 + 1 + 2 == gameState.getGiocatoriFinePartita().get(1).getPunteggioVittoria());
	}

	@Test
	public void G1VinceConPariPuntiVittoriaPariTesserePermesso() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(1);
		g2.setPunteggioVittoria(1);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 0)
				g2.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di carte politica
		for (int i = 0; i < 2; i++) {
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 1; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}

		// assegno loro tessereBonus(punti vittoria)
		g1.getTessereBonus().clear();
		g1.getTessereBonus().add(new BonusPuntiVittoria(2));
		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(2));

		List<Giocatore> vincitori = gameState.calcolaVincitore();
		assertTrue(g1 == vincitori.get(0));
		assertTrue(vincitori.size() == 1);
		
		assertEquals(1 + 2 + 5 + 3, vincitori.get(0).getPunteggioVittoria());
		assertEquals(2 + 1 + 2 + 3, gameState.getGiocatoriFinePartita().get(1).getPunteggioVittoria());
	}

	@Test
	public void g1VinceConPariPuntiVittoriaPariPuntiNobiltà() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(1);
		g2.setPunteggioVittoria(1);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(2));
		g2.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 1)
				g2.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di carte politica
		for (int i = 0; i < 2; i++) {
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 1; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 0; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}

		// assegno loro tessereBonus(punti vittoria)
		g1.getTessereBonus().clear();
		g1.getTessereBonus().add(new BonusPuntiVittoria(2));
		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(2));

		List<Giocatore> vincitori = gameState.calcolaVincitore();
		assertTrue(g1 == vincitori.get(0));
		assertTrue(vincitori.size() == 1);
		
		assertEquals(1 + 2 + 5 + 3, vincitori.get(0).getPunteggioVittoria());
		assertEquals(2 + 1 + 2 +3, gameState.getGiocatoriFinePartita().get(1).getPunteggioVittoria());
	}

	@Test
	public void g1g2vinceConPariTutto() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(1);
		g2.setPunteggioVittoria(1);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 1)
				g2.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di carte politica
		for (int i = 0; i < 1; i++) {
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 1; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}

		// assegno loro tessereBonus(punti vittoria)
		g1.getTessereBonus().clear();
		g1.getTessereBonus().add(new BonusPuntiVittoria(2));
		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(2));

		List<Giocatore> vincitori = gameState.calcolaVincitore();
		assertTrue(g1 == vincitori.get(0));
		assertTrue(g2 == vincitori.get(1));
		assertTrue(vincitori.size() == 2);
		
		assertEquals(11, vincitori.get(0).getPunteggioVittoria());
		assertEquals(11, gameState.getGiocatoriFinePartita().get(1).getPunteggioVittoria());
	}
	
	@Test
	public void quattroGiocatoriDueVinconoNobiltàUnoSecondo() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		Giocatore g3 = new Giocatore("G3");
		Giocatore g4 = new Giocatore("G4");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		giocatori.add(g3);
		giocatori.add(g4);
		GameState gameState = new GameState();

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(10);
		g2.setPunteggioVittoria(1);
		g3.setPunteggioVittoria(4);
		g4.setPunteggioVittoria(5);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
		g3.setAiutanti(new Aiutante(1));
		g4.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 3)
				g2.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 3)
				g3.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 2)
				g4.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 3; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g3.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g4.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		
		// assegno loro un certo numero di carte politica
		for (int i = 0; i < 1; i++) {
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g3.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g4.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}


		// assegno loro tessereBonus(punti vittoria)
		g1.getTessereBonus().clear();
		g1.getTessereBonus().add(new BonusPuntiVittoria(5));
		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(2));
		g3.getTessereBonus().clear();
		g3.getTessereBonus().add(new BonusPuntiVittoria(1));
		g4.getTessereBonus().clear();
		g4.getTessereBonus().add(new BonusPuntiVittoria(1));

		List<Giocatore> vincitori = gameState.calcolaVincitore();
		assertTrue(g1 == vincitori.get(0));
		assertTrue(vincitori.size() == 1);
		
		assertEquals(18, vincitori.get(0).getPunteggioVittoria());
		assertEquals(8, gameState.getGiocatoriFinePartita().get(0).getPunteggioVittoria());
		assertEquals(10, gameState.getGiocatoriFinePartita().get(1).getPunteggioVittoria());
		assertEquals(6, gameState.getGiocatoriFinePartita().get(2).getPunteggioVittoria());
	}
	
	@Test
	public void quattroGiocatoriUnoVinceNobiltàDueSecond() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		Giocatore g3 = new Giocatore("G3");
		Giocatore g4 = new Giocatore("G4");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		giocatori.add(g3);
		giocatori.add(g4);
		GameState gameState = new GameState();

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(10);
		g2.setPunteggioVittoria(1);
		g3.setPunteggioVittoria(4);
		g4.setPunteggioVittoria(5);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
		g3.setAiutanti(new Aiutante(1));
		g4.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 2)
				g2.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 3)
				g3.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 2)
				g4.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 2; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 3; i++) {
			g3.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g4.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}

		// assegno loro tessereBonus(punti vittoria)
		g1.getTessereBonus().clear();
		g1.getTessereBonus().add(new BonusPuntiVittoria(5));
		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(2));
		g3.getTessereBonus().clear();
		g3.getTessereBonus().add(new BonusPuntiVittoria(1));
		g4.getTessereBonus().clear();
		g4.getTessereBonus().add(new BonusPuntiVittoria(1));

		List<Giocatore> vincitori = gameState.calcolaVincitore();
		assertTrue(g1 == vincitori.get(0));
		assertTrue(vincitori.size() == 1);
				
		assertEquals(15, vincitori.get(0).getPunteggioVittoria());
		assertEquals(13, gameState.getGiocatoriFinePartita().get(0).getPunteggioVittoria());
		assertEquals(5, gameState.getGiocatoriFinePartita().get(1).getPunteggioVittoria());
		assertEquals(8, gameState.getGiocatoriFinePartita().get(2).getPunteggioVittoria());
	}
	
	@Test
	public void quattroGiocatoriUnoVinceNobiltàUnoSecondoNobiltàUnoZeroBonus() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		Giocatore g3 = new Giocatore("G3");
		Giocatore g4 = new Giocatore("G4");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		giocatori.add(g3);
		giocatori.add(g4);
		GameState gameState = new GameState();

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(3);
		g2.setPunteggioVittoria(5);
		g3.setPunteggioVittoria(2);
		g4.setPunteggioVittoria(1);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(1));
		g2.setAiutanti(new Aiutante(1));
		g3.setAiutanti(new Aiutante(1));
		g4.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 3)
				g2.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 2)
				g3.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 5)
				g4.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 3; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 2; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 4; i++) {
			g3.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g4.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		
		// assegno loro un certo numero di carte politica
		for (int i = 0; i < 1; i++) {
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g3.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g4.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}

		// assegno loro tessereBonus(punti vittoria)
		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(1));
		g3.getTessereBonus().clear();
		g3.getTessereBonus().add(new BonusPuntiVittoria(3));
		g4.getTessereBonus().clear();
		g4.getTessereBonus().add(new BonusPuntiVittoria(2));

		List<Giocatore> vincitori = gameState.calcolaVincitore();
		assertTrue(vincitori.contains(g2));
		assertTrue(vincitori.contains(g3));
		assertTrue(vincitori.contains(g4));
		assertTrue(vincitori.size() == 3);
		assertEquals(8, vincitori.get(0).getPunteggioVittoria());
		assertEquals(8, vincitori.get(1).getPunteggioVittoria());
		assertEquals(8, vincitori.get(2).getPunteggioVittoria());
		assertEquals(3, gameState.getGiocatoriFinePartita().get(gameState.getGiocatoriFinePartita().size()-1).getPunteggioVittoria());
	}
	
	@Test
	public void quattroGiocatoriUnoVinceNobiltàUnoSecondoNobiltàUnoZeroBonusEAiutantiMaggioriTrePareggianoMaunoVince() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		Giocatore g3 = new Giocatore("G3");
		Giocatore g4 = new Giocatore("G4");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		giocatori.add(g3);
		giocatori.add(g4);
		GameState gameState = new GameState();

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(3);
		g2.setPunteggioVittoria(4);
		g3.setPunteggioVittoria(2);
		g4.setPunteggioVittoria(1);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(100));
		g2.setAiutanti(new Aiutante(1));
		g3.setAiutanti(new Aiutante(2));
		g4.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 3)
				g2.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 2)
				g3.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 5)
				g4.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 3; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 2; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 4; i++) {
			g3.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g4.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		
		// assegno loro un certo numero di carte politica
		for (int i = 0; i < 1; i++) {
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g3.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g4.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}

		// assegno loro tessereBonus(punti vittoria)

		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(1));
		g3.getTessereBonus().clear();
		g3.getTessereBonus().add(new BonusPuntiVittoria(3));
		g4.getTessereBonus().clear();
		g4.getTessereBonus().add(new BonusPuntiVittoria(2));

		List<Giocatore> vincitori = gameState.calcolaVincitore();

		assertTrue(vincitori.contains(g3));
		assertTrue(vincitori.size() == 1);
		
		assertEquals(8, vincitori.get(0).getPunteggioVittoria());
		assertEquals(3, gameState.getGiocatoriFinePartita().get(3).getPunteggioVittoria());
		assertEquals(8, gameState.getGiocatoriFinePartita().get(2).getPunteggioVittoria());
		assertEquals(7, gameState.getGiocatoriFinePartita().get(1).getPunteggioVittoria());
	}
	
	@Test
	public void quattroGiocatoriTrePareggianoUnoHapiùCartePoliticaAiutantiTuttiUguali() throws IOException {
		Giocatore g1 = new Giocatore("G1");
		Giocatore g2 = new Giocatore("G2");
		Giocatore g3 = new Giocatore("G3");
		Giocatore g4 = new Giocatore("G4");
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		giocatori.add(g1);
		giocatori.add(g2);
		giocatori.add(g3);
		giocatori.add(g4);
		GameState gameState = new GameState();

		gameState.start(giocatori,"mappa1");
		gameState.getGiocatoriFinePartita().addAll(giocatori);

		// assegno loro punti vittoria
		g1.setPunteggioVittoria(3);
		g2.setPunteggioVittoria(5);
		g3.setPunteggioVittoria(2);
		g4.setPunteggioVittoria(1);

		// assegno loro aiutanti
		g1.setAiutanti(new Aiutante(100));
		g2.setAiutanti(new Aiutante(1));
		g3.setAiutanti(new Aiutante(1));
		g4.setAiutanti(new Aiutante(1));

		// assegno loro il punteggio nobiltà
		for (PunteggioNobiltà p : gameState.getPlanciaRe().getPercorsoNobiltà()) {
			if (p.getPuntiNobiltà() == 1)
				g1.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 3)
				g2.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 2)
				g3.setPunteggioNobiltà(p);
			if (p.getPuntiNobiltà() == 5)
				g4.setPunteggioNobiltà(p);
		}

		// assegno loro un certo numero di tessere permesso
		for (int i = 0; i < 3; i++) {
			g1.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 2; i++) {
			g2.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 4; i++) {
			g3.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g4.getTesserePermesso().add(gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte().get(i));
		}
		
		// assegno loro un certo numero di carte politica
		for (int i = 0; i < 1; i++) {
			g1.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g2.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 2; i++) {
			g3.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}
		for (int i = 0; i < 1; i++) {
			g4.getCartePolitica().add(gameState.getMazzoCartePolitica().getCarte().get(i));
		}

		// assegno loro tessereBonus(punti vittoria)

		g2.getTessereBonus().clear();
		g2.getTessereBonus().add(new BonusPuntiVittoria(1));
		g3.getTessereBonus().clear();
		g3.getTessereBonus().add(new BonusPuntiVittoria(3));
		g4.getTessereBonus().clear();
		g4.getTessereBonus().add(new BonusPuntiVittoria(2));

		List<Giocatore> vincitori = gameState.calcolaVincitore();

		assertTrue(vincitori.contains(g3));
		assertTrue(vincitori.size() == 1);
		
		assertEquals(8, vincitori.get(0).getPunteggioVittoria());
		assertEquals(3, gameState.getGiocatoriFinePartita().get(3).getPunteggioVittoria());
		assertEquals(8, gameState.getGiocatoriFinePartita().get(2).getPunteggioVittoria());
		assertEquals(8, gameState.getGiocatoriFinePartita().get(1).getPunteggioVittoria());
	}

}
