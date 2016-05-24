package game.azioni;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.event.CaretEvent;

import org.junit.BeforeClass;
import org.junit.Test;

import game.Aiutante;
import game.Balcone;
import game.CartaPolitica;
import game.Città;
import game.Colore;
import game.Consigliere;
import game.Emporio;
import game.GameState;
import game.Giocatore;
import game.PunteggioNobiltà;
import game.Re;
import game.TesseraPermesso;

public class CostruzioneAiutoReTest {

	static GameState gameState;
	
	@BeforeClass
	public static void init() throws IOException{
		gameState=new GameState();
		
	}
	
/*	@Test(expected=NullPointerException.class)
	public void EseguiAzioneNull() {
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		costruzioneAiutoRe.eseguiAzione(null);
	}*/
	
	//salvo 1,2,3,4 colori dei consiglieri e creo le carte politica con quei colori->li confronto e vedo se mi tornano il numero di monente giusto! ANCHE CON I MULTICOLOR
	//TEST ANCHE per la città che è piu lontana di uno
	@Test
	public void azioneBuonFine() {
		Città cittàCostruzione=null;
		CostruzioneAiutoRe costruzioneAiutoRe=new CostruzioneAiutoRe();
		Balcone balconeRe=gameState.getPlanciaRe().getBalconeRe();
		Consigliere consigliere=balconeRe.getConsigliere().element();
		ArrayList<CartaPolitica> carteGiocatore = new ArrayList<CartaPolitica>();
		carteGiocatore.add(new CartaPolitica(consigliere.getColore()));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		carteGiocatore.add(new CartaPolitica(new Colore("Multicolore")));
		for (Città c:gameState.getCittà()){
			if(c.getNome().equals("Hellar"))
				cittàCostruzione=c;
		}
		
		cittàCostruzione.aggiungiEmporio(new Emporio(new Colore("EmporioGiaPresente")));
		
		//set parametri per la costruzione
		costruzioneAiutoRe.setCittàCostruzione(cittàCostruzione);
		System.out.println("CittàCostruzione: "+cittàCostruzione+"\nNumeroEmporiPResenti: "+cittàCostruzione.getEmpori().size());
		
		costruzioneAiutoRe.setCarteGiocatore(carteGiocatore);
		System.out.println("CarteGiocatore: "+carteGiocatore);
		//set giocatorecorrente
		ArrayList<Emporio> empori=new ArrayList<Emporio>();
		empori.add(new Emporio(new Colore("C_Emporio")));
		gameState.setGiocatoreCorrente(new Giocatore(new Colore("giocatore"), carteGiocatore, new Aiutante(4), 0, 10, new PunteggioNobiltà(0, null), empori));
		System.out.println("soldi :"+gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		//System.out.println("città re: "+gameState.getPedinaRe().getCittà());

		costruzioneAiutoRe.eseguiAzione(gameState);
		System.out.println("CittàCostruzione: "+cittàCostruzione+"\nNumeroEmporiPResenti: "+cittàCostruzione.getEmpori().size());
		System.out.println("soldi dopo :"+gameState.getGiocatoreCorrente().getPunteggioRicchezza());

		//System.out.println("città re: "+gameState.getPedinaRe().getCittà());

		//gli passo 3 carte, di cui 2 multicolor: costo totale 6 
		assertEquals(2, gameState.getGiocatoreCorrente().getPunteggioRicchezza());
//		assertEquals(3, gameState.getGiocatoreCorrente().getAiutanti().getAiutante());
//		assertEquals(gameState.getPedinaRe().getCittà(), costruzioneAiutoRe);
//		assertEquals(gameState.getGiocatoreCorrente().getEmpori().get(0), empori.get(0));
//		assertEquals(empori, null);
		
	}

}
