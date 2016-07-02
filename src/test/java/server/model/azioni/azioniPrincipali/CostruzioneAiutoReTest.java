package server.model.azioni.azioniPrincipali;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.azioniDTO.CostruzioneAiutoReDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.bonus.BonusMoneta;
import server.model.bonus.BonusPuntiVittoria;
import server.model.game.Aiutante;
import server.model.game.Balcone;
import server.model.game.CartaPolitica;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Colore;
import server.model.game.Consigliere;
import server.model.game.Emporio;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import utility.Observer;

public class CostruzioneAiutoReTest {

	static GameState gameState;
	static Giocatore giocatore;
	static List<Notify> notify;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		notify = new ArrayList<>();
		giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		giocatori.add(new Giocatore("B"));
		giocatori.add(new Giocatore("C"));
		gameState=new GameState();
		Observer<Notify> observer = new Observer<Notify>() {

			@Override
			public void update(Notify c) {
				notify.add(c);
			}
		};
		gameState.registerObserver(observer);
		gameState.start(giocatori, "mappa1");
		gameState.setGiocatoreCorrente(giocatore);
	}
	
	@Test
	public void azioneBuonFine() {
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(10);
		gameState.getGiocatoreCorrente().aggiungiEmpori(Arrays.asList(new Emporio(new Colore("emporio"))));;
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		//set carte che passa il giocatore
		Balcone balconeRe=gameState.getPlanciaRe().getBalconeRe();
		Consigliere consigliere=balconeRe.getConsigliere().element();
		ArrayList<CartaPolitica> carteGiocatore = new ArrayList<CartaPolitica>();
		carteGiocatore.add(new CartaPolitica(consigliere.getColore()));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		costruzioneAiutoRe.setCarteGiocatore(carteGiocatore);
		
		//set cittàCostruzione
		CittàBonus cittàCostruzione=null;
		for (Città c:gameState.getCittà()){
			if(c.getNome().equals("Hellar")){
				cittàCostruzione=(CittàBonus) c;
				break;
				}
		}
		costruzioneAiutoRe.setCittàCostruzione(cittàCostruzione);
		
		//costruisco un emporio in hellar
		cittàCostruzione.getEmpori().clear();
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("EmporioGiaPresente")));
	
		//eseguo l'azione
		costruzioneAiutoRe.eseguiAzione(gameState);
		
		//DEVO CONSIDERARE I BONUS CHE POSSONO ESSERE ASSEGNATI
		int monete=0;
		int aiutanti=0;
		for(Bonus b: cittàCostruzione.getBonus()){
			if (b instanceof BonusMoneta)
				monete=((BonusMoneta) b).getMonete();
			if(b instanceof BonusAiutanti)
				aiutanti= ((BonusAiutanti) b).getAiutanti();
		}
		
		//pago 8 monete
		assertEquals(4+monete, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		//pago 1 aiutanete per l'emporio gia presente in hellar
		assertEquals(0+aiutanti, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
	}
	
	@Test
	public void azioneBuonFine2Carte() {
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(8);
		gameState.getGiocatoreCorrente().aggiungiEmpori(Arrays.asList(new Emporio(new Colore("emporio"))));
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		//set carte che passa il giocatore
		Balcone balconeRe=gameState.getPlanciaRe().getBalconeRe();
		Consigliere consigliere=balconeRe.getConsigliere().element();
		ArrayList<CartaPolitica> carteGiocatore = new ArrayList<CartaPolitica>();
		carteGiocatore.add(new CartaPolitica(consigliere.getColore()));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		costruzioneAiutoRe.setCarteGiocatore(carteGiocatore);
		
		//set cittàCostruzione
		CittàBonus cittàCostruzione=null;
		for (Città c:gameState.getCittà()){
			if(c.getNome().equals("Hellar")){
				cittàCostruzione=(CittàBonus) c;
				break;
				}
		}
		costruzioneAiutoRe.setCittàCostruzione(cittàCostruzione);
		
		//costruisco un emporio in hellar
		cittàCostruzione.getEmpori().clear();
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("EmporioGiaPresente")));
	
		//eseguo l'azione
		costruzioneAiutoRe.eseguiAzione(gameState);
		
		//DEVO CONSIDERARE I BONUS CHE POSSONO ESSERE ASSEGNATI
		int monete=0;
		int aiutanti=0;
		for(Bonus b: cittàCostruzione.getBonus()){
			if (b instanceof BonusMoneta)
				monete=((BonusMoneta) b).getMonete();
			if(b instanceof BonusAiutanti)
				aiutanti= ((BonusAiutanti) b).getAiutanti();
		}
		
		//pago 8 monete
		assertEquals(0+monete, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		//pago 1 aiutanete per l'emporio gia presente in hellar
		assertEquals(0+aiutanti, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
	}
	
	@Test
	public void azioneBuonFine1Carta() {
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(12);
		gameState.getGiocatoreCorrente().aggiungiEmpori(Arrays.asList(new Emporio(new Colore("emporio"))));
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		//set carte che passa il giocatore
		Balcone balconeRe=gameState.getPlanciaRe().getBalconeRe();
		Consigliere consigliere=balconeRe.getConsigliere().element();
		ArrayList<CartaPolitica> carteGiocatore = new ArrayList<CartaPolitica>();
		carteGiocatore.add(new CartaPolitica(consigliere.getColore()));
		costruzioneAiutoRe.setCarteGiocatore(carteGiocatore);
		
		//set cittàCostruzione
		CittàBonus cittàCostruzione=null;
		for (Città c:gameState.getCittà()){
			if(c.getNome().equals("Hellar")){
				cittàCostruzione=(CittàBonus) c;
				break;
				}
		}
		costruzioneAiutoRe.setCittàCostruzione(cittàCostruzione);
		
		//costruisco un emporio in hellar
		cittàCostruzione.getEmpori().clear();
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("EmporioGiaPresente")));
	
		//eseguo l'azione
		costruzioneAiutoRe.eseguiAzione(gameState);
		
		//DEVO CONSIDERARE I BONUS CHE POSSONO ESSERE ASSEGNATI
		int monete=0;
		int aiutanti=0;
		for(Bonus b: cittàCostruzione.getBonus()){
			if (b instanceof BonusMoneta)
				monete=((BonusMoneta) b).getMonete();
			if(b instanceof BonusAiutanti)
				aiutanti= ((BonusAiutanti) b).getAiutanti();
		}
		
		//pago 8 monete
		assertEquals(0+monete, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		//pago 1 aiutanete per l'emporio gia presente in hellar
		assertEquals(0+aiutanti, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
	}
	
	@Test
	public void controllaColoriFail(){
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		//set carte che passa il giocatore
		ArrayList<CartaPolitica> carteGiocatore = new ArrayList<CartaPolitica>();
		carteGiocatore.add(new CartaPolitica(new Colore("a")));
		
		costruzioneAiutoRe.setCarteGiocatore(carteGiocatore);
		notify.clear();

		//eseguo l'azione
		costruzioneAiutoRe.eseguiAzione(gameState);

		assertTrue(notify.size()==1);
		assertTrue(notify.get(0) instanceof MessageNotify);
	}
	
	@Test
	public void pagoAiutanteFail(){
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(0));
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		//set carte che passa il giocatore
		Balcone balconeRe=gameState.getPlanciaRe().getBalconeRe();
		Consigliere consigliere=balconeRe.getConsigliere().element();
		ArrayList<CartaPolitica> carteGiocatore = new ArrayList<CartaPolitica>();
		carteGiocatore.add(new CartaPolitica(consigliere.getColore()));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		costruzioneAiutoRe.setCarteGiocatore(carteGiocatore);
		
		//set cittàCostruzione
		CittàBonus cittàCostruzione=null;
		for (Città c:gameState.getCittà()){
			if(c.getNome().equals("Hellar")){
				cittàCostruzione=(CittàBonus) c;
				break;
				}
		}
		costruzioneAiutoRe.setCittàCostruzione(cittàCostruzione);
		
		//costruisco un emporio in hellar

		cittàCostruzione.getEmpori().clear();
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("EmporioGiaPresente")));
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("EmporioGiaPresente2")));
		
		notify.clear();

		//eseguo l'azione
		costruzioneAiutoRe.eseguiAzione(gameState);
		
		assertTrue(notify.size()==1);
		assertTrue(notify.get(0) instanceof MessageNotify);
	}
	
	@Test
	public void pagaFail(){
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(0);
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		//set carte che passa il giocatore
		Balcone balconeRe=gameState.getPlanciaRe().getBalconeRe();
		Consigliere consigliere=balconeRe.getConsigliere().element();
		ArrayList<CartaPolitica> carteGiocatore = new ArrayList<CartaPolitica>();
		carteGiocatore.add(new CartaPolitica(consigliere.getColore()));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		costruzioneAiutoRe.setCarteGiocatore(carteGiocatore);
		
		//set cittàCostruzione
		CittàBonus cittàCostruzione=null;
		for (Città c:gameState.getCittà()){
			if(c.getNome().equals("Hellar")){
				cittàCostruzione=(CittàBonus) c;
				break;
				}
		}
		costruzioneAiutoRe.setCittàCostruzione(cittàCostruzione);
		
		//costruisco un emporio in hellar
		cittàCostruzione.getEmpori().clear();
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("EmporioGiaPresente")));
	
		notify.clear();

		//eseguo l'azione
		costruzioneAiutoRe.eseguiAzione(gameState);
		
		assertTrue(notify.size()==1);
		assertTrue(notify.get(0) instanceof MessageNotify);
	}
	
	@Test
	public void giocatoreCorrenteFinisceEmpori(){
		int punteggioVittoria=gameState.getGiocatoreCorrente().getPunteggioVittoria();
		gameState.getGiocatoreCorrente().setAiutanti(new Aiutante(1));
		gameState.getGiocatoreCorrente().setPunteggioRicchezza(10);
		gameState.getGiocatoreCorrente().getEmpori().clear();
		gameState.getGiocatoreCorrente().aggiungiEmpori(Arrays.asList(new Emporio(new Colore("emporio"))));
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		//set carte che passa il giocatore
		Balcone balconeRe=gameState.getPlanciaRe().getBalconeRe();
		Consigliere consigliere=balconeRe.getConsigliere().element();
		ArrayList<CartaPolitica> carteGiocatore = new ArrayList<CartaPolitica>();
		carteGiocatore.add(new CartaPolitica(consigliere.getColore()));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		costruzioneAiutoRe.setCarteGiocatore(carteGiocatore);
		
		//set cittàCostruzione
		CittàBonus cittàCostruzione=null;
		for (Città c:gameState.getCittà()){
			if(c.getNome().equals("Hellar")){
				cittàCostruzione=(CittàBonus) c;
				break;
				}
		}
		costruzioneAiutoRe.setCittàCostruzione(cittàCostruzione);
		
		//costruisco un emporio in hellar
		cittàCostruzione.getEmpori().clear();
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("EmporioGiaPresente")));
	
		//eseguo l'azione
		costruzioneAiutoRe.eseguiAzione(gameState);
		
		//DEVO CONSIDERARE I BONUS CHE POSSONO ESSERE ASSEGNATI
		int vittoria=0;
		for(Bonus b: cittàCostruzione.getBonus()){
			if(b instanceof BonusPuntiVittoria)
				vittoria= ((BonusPuntiVittoria) b).getPuntiVittoria();
		}
		
		assertTrue(gameState.isUltimoGiro());
		assertEquals(punteggioVittoria+3+vittoria, gameState.getGiocatoreCorrente().getPunteggioVittoria());
	}
	
	@Test
	public void testGetAzioneDTO() {
		CostruzioneAiutoRe costruzione=new CostruzioneAiutoRe();
		assertTrue(costruzione.getAzioneDTO() instanceof CostruzioneAiutoReDTO);
	}

	@Test(expected=NullPointerException.class)
	public void testSetCittàCostruzione() {
		CostruzioneAiutoRe costruzione=new CostruzioneAiutoRe();
		costruzione.setCittàCostruzione(null);
	}

	@Test(expected=NullPointerException.class)
	public void testSetCarteGiocatore() {
		CostruzioneAiutoRe costruzione=new CostruzioneAiutoRe();
		costruzione.setCarteGiocatore(null);
	}

}
