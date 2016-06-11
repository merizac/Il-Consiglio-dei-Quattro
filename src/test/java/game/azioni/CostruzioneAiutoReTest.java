package game.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.event.CaretEvent;

import org.junit.BeforeClass;
import org.junit.Test;

import server.model.azioni.azioniPrincipali.CostruzioneAiutoRe;
import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.bonus.BonusMoneta;
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
import server.model.game.PunteggioNobiltà;
import server.model.game.Re;
import server.model.game.TesseraPermesso;

public class CostruzioneAiutoReTest {

	static GameState gameState;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		ArrayList<Giocatore> giocatori=new ArrayList<>();
		Giocatore giocatore=new Giocatore("Giocatore");
		giocatori.add(giocatore);
		gameState.start(giocatori);
		
	}
	
/*	@Test(expected=NullPointerException.class)
	public void EseguiAzioneNull() {
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		costruzioneAiutoRe.eseguiAzione(null);
	}*/
	
	@Test
	public void azioneBuonFine() {
		
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
		assertEquals(2+monete, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		//pago 1 aiutanete per l'emporio gia presente in hellar
		assertEquals(0+aiutanti, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
	}

}
