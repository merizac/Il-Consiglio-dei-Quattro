package server.model.azioni.azioniPrincipali;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.bonus.BonusMoneta;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.Aiutante;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Colore;
import server.model.game.Emporio;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class CostruzioneTesseraPermessoTest {

	static GameState gameState;
	static Giocatore giocatore;
	static List<Notify> notify;
	static Regione regione;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori = new ArrayList<>();
		notify = new ArrayList<>();
		giocatore = new Giocatore("Giocatore");
		giocatori.add(giocatore);
		giocatori.add(new Giocatore("B"));
		giocatori.add(new Giocatore("C"));
		gameState = new GameState();
		Observer<Notify> observer = new Observer<Notify>() {

			@Override
			public void update(Notify c) {
				notify.add(c);
			}
		};
		gameState.registerObserver(observer);
		gameState.start(giocatori, "mappa1");
		gameState.setGiocatoreCorrente(giocatore);
		regione = gameState.getRegioni().get(0);
	}

	@Test
	public void testEseguiAzioneBuonFine() {

		for (Città c : gameState.getRegioni().get(0).getCittàRegione()) {
			c.getEmpori().clear();
		}

		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(5));
		gameState.getGiocatoreCorrente().getEmpori().clear();
		gameState.getGiocatoreCorrente()
				.aggiungiEmpori(Arrays.asList(new Emporio(gameState.getGiocatoreCorrente().getColoreGiocatore())));
		gameState.getGiocatoreCorrente().setPunteggioVittoria(0);
		// perndo le città per il test
		CittàBonus cittàCostruzione = null;
		CittàBonus cittàVicina = null;
		for (Città c : regione.getCittàRegione()) {
			if (c.getNome().equals("Arkon")) {
				cittàCostruzione = (CittàBonus) c;
				continue;
			} else if (c.getNome().equals("Burgen")) {
				cittàVicina = (CittàBonus) c;
				continue;
			}
		}

		cittàVicina.aggiungiEmporio(new Emporio(gameState.getGiocatoreCorrente().getColoreGiocatore()));

		// metto l'emporio sulla città su cui costruisco
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("Presente")));

		// creo la tessera permesso
		ArrayList<Città> città = new ArrayList<>();
		città.add(cittàCostruzione);
		TesseraPermesso tesseraPermesso = new TesseraPermesso(città, Arrays.asList(new BonusAiutanti(2)), regione);

		// creo e setto i parametri per l'azione e la eseguo
		CostruzioneTesseraPermesso azione = new CostruzioneTesseraPermesso();
		azione.setCittàCostruzione(cittàCostruzione);
		azione.setTesseraPermessoScoperta(tesseraPermesso);

		int monete = 0;
		int aiutanti = 0;
		int vittoria = 0;
		// tengo conto dei bonus della città su cui costruisco
		for (Bonus b : cittàCostruzione.getBonus()) {
			if (b instanceof BonusAiutanti) {
				aiutanti += ((BonusAiutanti) b).getAiutanti();
				continue;
			}
			if (b instanceof BonusMoneta) {
				monete += ((BonusMoneta) b).getMonete();
				continue;
			}
			if (b instanceof BonusPuntiVittoria) {
				vittoria += ((BonusPuntiVittoria) b).getPuntiVittoria();
				continue;
			}
		}

		// tengo conto dei bonus della città vicina
		for (Bonus b : cittàVicina.getBonus()) {
			if (b instanceof BonusAiutanti) {
				aiutanti += ((BonusAiutanti) b).getAiutanti();
				continue;
			}
			if (b instanceof BonusMoneta) {
				monete += ((BonusMoneta) b).getMonete();
				continue;
			}
			if (b instanceof BonusPuntiVittoria) {
				vittoria += ((BonusPuntiVittoria) b).getPuntiVittoria();
				continue;
			}
		}

		gameState.getGiocatoreCorrente().setPunteggioRicchezza(10);

		azione.eseguiAzione(gameState);

		if (gameState.getGiocatoreCorrente().getEmpori().isEmpty())
			vittoria += 3;
		if (gameState.getGiocatoreCorrente().getPunteggioNobiltà().getPuntiNobiltà()==2)
			vittoria += 2;
		

		assertTrue(cittàCostruzione.getEmpori().size() == 2);
		assertEquals(aiutanti + 4, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
		assertEquals(10 + monete, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertTrue(gameState.getGiocatoreCorrente().getTesserePermessoUsate().contains(tesseraPermesso));
		assertTrue(gameState.getGiocatoreCorrente().getTesserePermesso().size() == 0);
	}

	@Test
	public void pagoAiutantiFail() {
		// perndo le città per il test
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(0));
		CittàBonus cittàCostruzione = null;
		CittàBonus cittàVicina = null;
		for (Città c : regione.getCittàRegione()) {
			if (c.getNome().equals("Arkon")) {
				cittàCostruzione = (CittàBonus) c;
				continue;
			} else if (c.getNome().equals("Burgen")) {
				cittàVicina = (CittàBonus) c;
				continue;
			}
		}

		cittàVicina.aggiungiEmporio(gameState.getGiocatoreCorrente().getEmpori().get(0));

		// metto l'emporio sulla città su cui costruisco
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("Presente")));

		// creo la tessera permesso
		ArrayList<Città> città = new ArrayList<>();
		città.add(cittàCostruzione);
		TesseraPermesso tesseraPermesso = new TesseraPermesso(città, Arrays.asList(new BonusAiutanti(2)), regione);

		// creo e setto i parametri per l'azione e la eseguo
		CostruzioneTesseraPermesso azione = new CostruzioneTesseraPermesso();
		azione.setCittàCostruzione(cittàCostruzione);
		azione.setTesseraPermessoScoperta(tesseraPermesso);

		int monete = 0;
		int aiutanti = 0;
		int vittoria = 0;
		// tengo conto dei bonus della città su cui costruisco
		for (Bonus b : cittàCostruzione.getBonus()) {
			if (b instanceof BonusAiutanti) {
				aiutanti += ((BonusAiutanti) b).getAiutanti();
				continue;
			}
			if (b instanceof BonusMoneta) {
				monete += ((BonusMoneta) b).getMonete();
				continue;
			}
			if (b instanceof BonusPuntiVittoria) {
				vittoria += ((BonusPuntiVittoria) b).getPuntiVittoria();
				continue;
			}
		}

		// tengo conto dei bonus della città vicina
		for (Bonus b : cittàVicina.getBonus()) {
			if (b instanceof BonusAiutanti) {
				aiutanti += ((BonusAiutanti) b).getAiutanti();
				continue;
			}
			if (b instanceof BonusMoneta) {
				monete += ((BonusMoneta) b).getMonete();
				continue;
			}
			if (b instanceof BonusPuntiVittoria) {
				vittoria += ((BonusPuntiVittoria) b).getPuntiVittoria();
				continue;
			}
		}

		gameState.getGiocatoreCorrente().setPunteggioRicchezza(10);

		notify.clear();
		azione.eseguiAzione(gameState);

		if (gameState.getGiocatoreCorrente().getEmpori().isEmpty())
			vittoria += 3;

		assertEquals(1, notify.size());
		assertTrue(notify.get(0) instanceof MessageNotify);
	}

	@Test
	public void giocatoreRiceveBonusRegione() {
		int monete = 0;
		int aiutanti = 0;
		int vittoria = 0;

		// rimuovo tutti gli empori da tutte le città della regione
		for (Città c : gameState.getRegioni().get(0).getCittàRegione()) {
			c.getEmpori().clear();
		}

		assertEquals(vittoria,gameState.getGiocatoreCorrente().getPunteggioVittoria());
		gameState.getGiocatoreCorrente().setPunteggioVittoria(0);
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));
		gameState.getGiocatoreCorrente().getEmpori().clear();
		gameState.getGiocatoreCorrente()
				.aggiungiEmpori(Arrays.asList(new Emporio(gameState.getGiocatoreCorrente().getColoreGiocatore()),
						new Emporio(gameState.getGiocatoreCorrente().getColoreGiocatore())));
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(10);

		// perndo le città per il test
		CittàBonus cittàCostruzione = null;
		for (Città c : regione.getCittàRegione()) {
			if (c.getNome().equals("Arkon")) {
				cittàCostruzione = (CittàBonus) c;
				continue;
			} else
				c.aggiungiEmporio(new Emporio(gameState.getGiocatoreCorrente().getColoreGiocatore()));
			// tengo conto dei bonus delle città della regione
			if (c instanceof CittàBonus) {
				for (Bonus b : ((CittàBonus) c).getBonus()) {
					if (b instanceof BonusAiutanti) {
						aiutanti += ((BonusAiutanti) b).getAiutanti();
						continue;
					}
					if (b instanceof BonusMoneta) {
						monete += ((BonusMoneta) b).getMonete();
						continue;
					}
					if (b instanceof BonusPuntiVittoria) {
						vittoria += ((BonusPuntiVittoria) b).getPuntiVittoria();
						continue;
					}
				}
			}

		}

		// metto l'emporio sulla città su cui costruisco
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("Presente")));

		TesseraPermesso tesseraPermesso = null;
		// creo la tessera permesso
		for (TesseraPermesso tessera : regione.getMazzoTesserePermesso().getCarte()) {
			if (tessera.getCittà().contains(cittàCostruzione)) {
				tesseraPermesso = tessera;
				
				System.out.println("Tessera: "+tesseraPermesso.getBonus());
				break;
			}
		}

		// creo e setto i parametri per l'azione e la eseguo
		CostruzioneTesseraPermesso azione = new CostruzioneTesseraPermesso();
		azione.setCittàCostruzione(cittàCostruzione);
		azione.setTesseraPermessoScoperta(tesseraPermesso);

		// tengo conto dei bonus della città su cui costruisco
		for (Bonus b : cittàCostruzione.getBonus()) {
			if (b instanceof BonusAiutanti) {
				aiutanti += ((BonusAiutanti) b).getAiutanti();
				continue;
			}
			if (b instanceof BonusMoneta) {
				monete += ((BonusMoneta) b).getMonete();
				continue;
			}
			if (b instanceof BonusPuntiVittoria) {
				vittoria += ((BonusPuntiVittoria) b).getPuntiVittoria();
				continue;
			}
		}

		gameState.getGiocatoreCorrente().setPunteggioRicchezza(10);

		Bonus bonusPremioRe=gameState.getPlanciaRe().getBonusPremioRe().get(0);
		int nbonusRe=gameState.getPlanciaRe().getBonusPremioRe().size();
		
		azione.eseguiAzione(gameState);
		
		if (gameState.getGiocatoreCorrente().getEmpori().isEmpty())
			vittoria += 3;
		
		assertTrue(cittàCostruzione.getEmpori().size() == 2);
		assertEquals(aiutanti,gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
		assertEquals(10 + monete,gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		assertEquals(vittoria,gameState.getGiocatoreCorrente().getPunteggioVittoria());
		assertTrue(gameState.getGiocatoreCorrente().getTesserePermessoUsate().contains(tesseraPermesso));
		assertTrue(gameState.getGiocatoreCorrente().getTesserePermesso().size()== 0);
		assertEquals(2, gameState.getGiocatoreCorrente().getTessereBonus().size());
		assertTrue(gameState.getGiocatoreCorrente().getTessereBonus().get(0)==regione.getBonusRegione());
		assertTrue(gameState.getGiocatoreCorrente().getTessereBonus().get(1)==bonusPremioRe);
		assertTrue(gameState.getGiocatoreCorrente().getTessereBonus().get(1)!=gameState.getPlanciaRe().getBonusPremioRe().get(0));
		assertTrue(regione.isBonusAssegnato());
		assertEquals(nbonusRe-1, gameState.getPlanciaRe().getBonusPremioRe().size());

	}

	@Test
	public void testSetandGetTesseraPermesso() {
		TesseraPermesso tesseraPermessoScoperta = gameState.getRegioni().get(0).getMazzoTesserePermesso().getCarte()
				.get(0);
		CostruzioneTesseraPermesso costruzioneTesseraPermesso = new CostruzioneTesseraPermesso();
		costruzioneTesseraPermesso.setTesseraPermessoScoperta(tesseraPermessoScoperta);
		assertTrue(tesseraPermessoScoperta == costruzioneTesseraPermesso.getTesseraPermessoScoperta());
		assertTrue(costruzioneTesseraPermesso.getTesseraPermessoScoperta() instanceof TesseraPermesso);
	}

	@Test
	public void testSetandGetCittàCostruzione() {
		Città cittàCostruzione = gameState.getRegioni().get(0).getCittàRegione().get(0);
		CostruzioneTesseraPermesso costruzioneTesseraPermesso = new CostruzioneTesseraPermesso();
		costruzioneTesseraPermesso.setCittàCostruzione(cittàCostruzione);
		assertTrue(cittàCostruzione == costruzioneTesseraPermesso.getCittàCostruzione());
		assertTrue(costruzioneTesseraPermesso.getCittàCostruzione() instanceof Città);
	}

	@Test
	public void testGetAzioneDTO() {
		CostruzioneTesseraPermesso costruzioneTesseraPermesso = new CostruzioneTesseraPermesso();
		assertTrue(costruzioneTesseraPermesso.getAzioneDTO() instanceof CostruzioneTesseraPermessoDTO);
	}
}
