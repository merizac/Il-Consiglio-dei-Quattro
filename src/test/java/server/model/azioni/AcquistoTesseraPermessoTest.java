package server.model.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;
import server.controller.Controller;
import server.model.azioni.Azione;
import server.model.azioni.azioniBonus.BonusGettoneN;
import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.bonus.BonusAzionePrincipale;
import server.model.bonus.BonusCartePolitica;
import server.model.bonus.BonusMoneta;
import server.model.bonus.BonusNobiltà;
import server.model.bonus.BonusPuntiNobiltà;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.CartaPolitica;
import server.model.game.Colore;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.PunteggioNobiltà;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.macchinaStati.StartEnd;
import server.model.macchinaStati.Stato11;
import server.model.macchinaStati.StatoBonus;
import server.model.notify.AvversarioNotify;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class AcquistoTesseraPermessoTest {

	static GameState gameState;
	static List<Notify> notify;
	static Regione regione;
	static TesseraPermesso tesseraScoperta;

	@BeforeClass
	public static void init() throws IOException {
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		notify = new ArrayList<>();
		Giocatore giocatore = new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState = new GameState();
		Observer<Notify> observer = new Observer<Notify>() {

			@Override
			public void update(Notify c) {
				notify.add(c);
			}
		};
		gameState.registerObserver(observer);
		gameState.start(giocatori, "mappa1");


		regione = gameState.getRegioni().get(0);
		tesseraScoperta = regione.getTesserePermessoScoperte().get(0);
	}

	@Test
	public void testEseguiAzioneConCarteGiocatoreVuoto() {
		notify.clear();
		AcquistoTesseraPermesso acquisto = new AcquistoTesseraPermesso();

		// creo array per le carte del giocatore
		ArrayList<CartaPolitica> cartePolitiche = new ArrayList<>();
		acquisto.setCarteGiocatore(cartePolitiche);

		acquisto.eseguiAzione(gameState);
		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test
	public void testEseguiAzioneCarteSbagliate() {
		notify.clear();
		AcquistoTesseraPermesso acquisto = new AcquistoTesseraPermesso();

		// creo array per le carte del giocatore
		ArrayList<CartaPolitica> cartePolitiche = new ArrayList<>();
		cartePolitiche.add(new CartaPolitica(new Colore("A")));
		acquisto.setCarteGiocatore(cartePolitiche);

		// setto la regione
		acquisto.setRegione(regione);

		// setto la tesserapermesso
		acquisto.setTesseraScoperta(tesseraScoperta);
		acquisto.eseguiAzione(gameState);

		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test
	public void testEseguiAzioneNonHaAbbastaMonete() {
		notify.clear();
		AcquistoTesseraPermesso acquisto = new AcquistoTesseraPermesso();

		// creo array per le carte del giocatore
		ArrayList<CartaPolitica> cartePolitiche = new ArrayList<>();
		cartePolitiche.add(new CartaPolitica(regione.getBalcone().getConsigliere().element().getColore()));
		acquisto.setCarteGiocatore(cartePolitiche);

		// setto la regione
		acquisto.setRegione(regione);

		// setto la tesserapermesso
		acquisto.setTesseraScoperta(tesseraScoperta);
		acquisto.eseguiAzione(gameState);

		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test
	public void testEseguiAzioneCarteOKColoriOKMoneteOKNonhaBonusNobiltà() {
		notify.clear();
		gameState.getGiocatoreCorrente().getCartePolitica().clear();

		int numeroCartePolitica = gameState.getGiocatoreCorrente().getCartePolitica().size();
		int numeroCartePoliticaMazzo = gameState.getMazzoCartePolitica().getCarte().size();
		int monete = gameState.getGiocatoreCorrente().getPunteggioRicchezza();
		int aiutanti = gameState.getGiocatoreCorrente().getAiutanti().getAiutante();

		AcquistoTesseraPermesso acquisto = new AcquistoTesseraPermesso();

		// creo array per le carte del giocatore
		ArrayList<CartaPolitica> cartePolitica = new ArrayList<>();
		cartePolitica.add(new CartaPolitica(regione.getBalcone().getConsigliere().element().getColore()));
		cartePolitica.add(new CartaPolitica(new Colore("Multicolore")));
		cartePolitica.add(new CartaPolitica(new Colore("Multicolore")));
		cartePolitica.add(new CartaPolitica(new Colore("Multicolore")));
		acquisto.setCarteGiocatore(cartePolitica);
		gameState.getGiocatoreCorrente().getCartePolitica().addAll(cartePolitica);

		// setto la regione
		acquisto.setRegione(regione);

		// setto la tesserapermesso
		acquisto.setTesseraScoperta(tesseraScoperta);

		// setto percorso nobiltà
		gameState.getGiocatoreCorrente().setPunteggioNobiltà(gameState.getPlanciaRe().getPercorsoNobiltà().get(1));

		// tolgo tutti i bonus e metto bonus che mi interessano
		tesseraScoperta.getBonus().clear();
		tesseraScoperta.getBonus().add(new BonusAiutanti(1));
		StartEnd stato11=new StartEnd(gameState);
		gameState.setStato(stato11);
	
		acquisto.eseguiAzione(gameState);

		assertTrue(numeroCartePolitica == gameState.getGiocatoreCorrente().getCartePolitica().size());
		assertTrue(numeroCartePoliticaMazzo + 4 == gameState.getMazzoCartePolitica().getCarte().size());
		assertTrue(!(tesseraScoperta == regione.getTesserePermessoScoperte().get(0)));
		assertTrue(regione.getTesserePermessoScoperte().get(0) != null);

		assertTrue(gameState.getStato() instanceof StartEnd);

		assertEquals(monete - 3, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertEquals(1 + aiutanti, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());

		System.out.println(notify);
		assertTrue(notify.get(notify.size() - 4) instanceof GameStateNotify);
		assertTrue(notify.get(notify.size() - 2) instanceof GiocatoreNotify);
		assertTrue(notify.get(notify.size() - 1) instanceof MessageNotify);
	}

	@Test
	public void testEseguiAzioneCarteOKColoriOKMoneteOKPossiedeBonus() {
		notify.clear();
		gameState.getGiocatoreCorrente().getCartePolitica().clear();
		

		int numeroCartePolitica = gameState.getGiocatoreCorrente().getCartePolitica().size();
		int numeroCartePoliticaMazzo = gameState.getMazzoCartePolitica().getCarte().size();
		int monete = gameState.getGiocatoreCorrente().getPunteggioRicchezza();
		int aiutanti = gameState.getGiocatoreCorrente().getAiutanti().getAiutante();

		AcquistoTesseraPermesso acquisto = new AcquistoTesseraPermesso();

		// creo array per le carte del giocatore
		ArrayList<CartaPolitica> cartePolitica = new ArrayList<>();
		cartePolitica.add(new CartaPolitica(regione.getBalcone().getConsigliere().element().getColore()));
		cartePolitica.add(new CartaPolitica(new Colore("Multicolore")));
		cartePolitica.add(new CartaPolitica(new Colore("Multicolore")));
		acquisto.setCarteGiocatore(cartePolitica);
		gameState.getGiocatoreCorrente().getCartePolitica().addAll(cartePolitica);

		// setto la regione
		acquisto.setRegione(regione);

		// setto la tesserapermesso
		acquisto.setTesseraScoperta(tesseraScoperta);

		// setto percorso nobiltà
		gameState.getGiocatoreCorrente().setPunteggioNobiltà(gameState.getPlanciaRe().getPercorsoNobiltà().get(1));

		// tolgo tutti i bonus e metto bonus che mi interessano
		tesseraScoperta.getBonus().clear();
		tesseraScoperta.getBonus().add(new BonusPuntiNobiltà(1));

		// salvo i bonus del percorso nobiltà
		int vittoria = 0;
		int carteP = 0;
		for (Bonus b : gameState.getPlanciaRe().getPercorsoNobiltà().get(2).getBonus()) {
			if (b instanceof BonusPuntiVittoria) {
				vittoria += ((BonusPuntiVittoria) b).getPuntiVittoria();
				continue;
			}
			if (b instanceof BonusMoneta) {
				monete += ((BonusMoneta) b).getMonete();
				continue;
			}
			if (b instanceof BonusCartePolitica) {
				carteP += ((BonusCartePolitica) b).getCartePolitica();
				continue;
			}
		}

		acquisto.eseguiAzione(gameState);

		assertTrue(numeroCartePolitica + carteP == gameState.getGiocatoreCorrente().getCartePolitica().size());
		assertTrue(numeroCartePoliticaMazzo + 3 - carteP == gameState.getMazzoCartePolitica().getCarte().size());
		assertTrue(!(tesseraScoperta == regione.getTesserePermessoScoperte().get(0)));
		assertTrue(regione.getTesserePermessoScoperte().get(0) != null);

		assertTrue(gameState.getStato() instanceof StartEnd);

		assertEquals(monete - 6, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertEquals(aiutanti, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
		assertEquals(vittoria, gameState.getGiocatoreCorrente().getPunteggioVittoria());

		assertTrue(notify.get(notify.size() - 4) instanceof GameStateNotify);
		assertTrue(notify.get(notify.size() - 2) instanceof GiocatoreNotify);
		assertTrue(notify.get(notify.size() - 3) instanceof AvversarioNotify);
		assertTrue(notify.get(notify.size() - 1) instanceof MessageNotify);
	}

	@Test
	public void testEseguiAzioneCarteOKColoriOKMoneteOKPossiedeBonusScattoTransizioneBonus() {
		notify.clear();
		gameState.getGiocatoreCorrente().getCartePolitica().clear();
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(10);
		
		int numeroCartePolitica = gameState.getGiocatoreCorrente().getCartePolitica().size();
		int numeroCartePoliticaMazzo = gameState.getMazzoCartePolitica().getCarte().size();
		int monete = gameState.getGiocatoreCorrente().getPunteggioRicchezza();
		int aiutanti = gameState.getGiocatoreCorrente().getAiutanti().getAiutante();

		AcquistoTesseraPermesso acquisto = new AcquistoTesseraPermesso();

		// creo array per le carte del giocatore
		ArrayList<CartaPolitica> cartePolitica = new ArrayList<>();
		cartePolitica.add(new CartaPolitica(regione.getBalcone().getConsigliere().element().getColore()));
		cartePolitica.add(new CartaPolitica(new Colore("Multicolore")));
		cartePolitica.add(new CartaPolitica(new Colore("Multicolore")));
		acquisto.setCarteGiocatore(cartePolitica);
		gameState.getGiocatoreCorrente().getCartePolitica().addAll(cartePolitica);

		// setto la regione
		acquisto.setRegione(regione);

		// setto la tesserapermesso
		acquisto.setTesseraScoperta(tesseraScoperta);

		// setto percorso nobiltà
		gameState.getGiocatoreCorrente().setPunteggioNobiltà(gameState.getPlanciaRe().getPercorsoNobiltà().get(2));

		//setto bonusNobiltà giocatore
		gameState.getGiocatoreCorrente().getBonusNobiltà().add(new BonusGettoneN());
		
		// tolgo tutti i bonus e metto bonus che mi interessano
		tesseraScoperta.getBonus().clear();
		tesseraScoperta.getBonus().add(new BonusPuntiNobiltà(2));
		
		// salvo i bonus del percorso nobiltà
		int vittoria = 0;
		int carteP = 0;
		for (Bonus b : gameState.getPlanciaRe().getPercorsoNobiltà().get(3).getBonus()) {
			if (b instanceof BonusPuntiVittoria) {
				vittoria += ((BonusPuntiVittoria) b).getPuntiVittoria();
				continue;
			}
			if (b instanceof BonusMoneta) {
				monete += ((BonusMoneta) b).getMonete();
				continue;
			}
			if (b instanceof BonusCartePolitica) {
				carteP += ((BonusCartePolitica) b).getCartePolitica();
				continue;
			}
		}
		System.out.println(gameState.getGiocatoreCorrente().getPunteggioNobiltà());
		System.out.println(gameState.getStato());
		
		Stato11 stato11=new Stato11(gameState);
		gameState.setStato(stato11);
		acquisto.eseguiAzione(gameState);

		assertTrue(numeroCartePolitica + carteP == gameState.getGiocatoreCorrente().getCartePolitica().size());
		assertTrue(numeroCartePoliticaMazzo + 3 - carteP == gameState.getMazzoCartePolitica().getCarte().size());
		assertTrue(!(tesseraScoperta == regione.getTesserePermessoScoperte().get(0)));
		assertTrue(regione.getTesserePermessoScoperte().get(0) != null);

		assertTrue(gameState.getStato() instanceof StatoBonus);

		assertEquals(monete - 6, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertEquals(aiutanti, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
		assertEquals(vittoria+2, gameState.getGiocatoreCorrente().getPunteggioVittoria());

		/*assertTrue(notify.get(notify.size() - 3) instanceof GameStateNotify);
		assertTrue(notify.get(notify.size() - 1) instanceof GiocatoreNotify);
		assertTrue(notify.get(notify.size() - 4) instanceof BonusNotify);
		assertTrue(notify.get(notify.size() - 2) instanceof AvversarioNotify);*/
		
	}
	
	@Test
	public void testGetCarteGiocatore() {
		AcquistoTesseraPermesso mossa = new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		ArrayList<CartaPolitica> c = new ArrayList<>();
		c.add(new CartaPolitica(new Colore("C1")));
		c.add(new CartaPolitica(new Colore("C2")));
		mossa.setCarteGiocatore(c);

		assertTrue(c == mossa.getCarteGiocatore());
	}

	@Test
	public void testSetCarteGiocatore() {
		AcquistoTesseraPermesso mossa = new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		ArrayList<CartaPolitica> c = new ArrayList<>();
		c.add(new CartaPolitica(new Colore("C1")));
		c.add(new CartaPolitica(new Colore("C2")));
		mossa.setCarteGiocatore(c);

		assertTrue(c == mossa.getCarteGiocatore());
	}

	@Test
	public void testGetRegione() {
		AcquistoTesseraPermesso mossa = new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		assertTrue(regione == mossa.getRegione());
	}

	@Test
	public void testSetRegione() {
		AcquistoTesseraPermesso mossa = new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		assertTrue(regione == mossa.getRegione());
	}

	@Test
	public void testGetTesseraScoperta() {
		AcquistoTesseraPermesso mossa = new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		mossa.setTesseraScoperta(tesseraScoperta);
		assertTrue(tesseraScoperta == mossa.getTesseraScoperta());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetTesseraScopertaNull() {
		AcquistoTesseraPermesso mossa = new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		mossa.setTesseraScoperta(null);
	}

	@Test
	public void testSetIndiceTesseraScoperta() {
		AcquistoTesseraPermesso mossa = new AcquistoTesseraPermesso();
		mossa.setRegione(regione);
		mossa.setTesseraScoperta(tesseraScoperta);
		assertTrue(tesseraScoperta == mossa.getTesseraScoperta());
	}

	public void testGetAzioneDTO() {
		AcquistoTesseraPermesso acquisto = new AcquistoTesseraPermesso();
		assertTrue(acquisto.getAzioneDTO() instanceof AcquistoTesseraPermessoDTO);
	}
}
