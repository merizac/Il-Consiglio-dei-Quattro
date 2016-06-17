package server.model.game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.model.game.CartaPolitica;
import server.model.game.Colore;
import server.model.game.Mazzo;

public class MazzoTest {

	private static ArrayList<CartaPolitica> carte;
	static Mazzo<CartaPolitica> mazzo;
	
	@BeforeClass
	public static void init(){
//		carte.add(new CartaPolitica(new Colore("1")));
//		carte.add(new CartaPolitica(new Colore("2")));
//		mazzo=new Mazzo<CartaPolitica>();
	}
	
	@Test
	public void testAggiungiCarta() {
		ArrayList<CartaPolitica> carte=new ArrayList<CartaPolitica>();
		CartaPolitica carta=new CartaPolitica(new Colore("1"));
		carte.add(carta);
		Mazzo<CartaPolitica> m=new Mazzo<>();
		m.aggiungiCarta(carta);
		assertEquals(new Mazzo<>(carte).getCarte(), m.getCarte());
	}
	
	@Test
	public void testGetCarte() {
		Mazzo<CartaPolitica> mazzo=new Mazzo<>();
		ArrayList<CartaPolitica> carte=new ArrayList<CartaPolitica>();
		CartaPolitica carta=new CartaPolitica(new Colore("1"));
		carte.add(carta);
		mazzo.aggiungiCarta(carta);
		assertEquals(carte, mazzo.getCarte());
	}

	@Test
	public void testPescaCarte() {
		ArrayList<CartaPolitica> carte=new ArrayList<CartaPolitica>();
		CartaPolitica carta=new CartaPolitica(new Colore("1"));
		CartaPolitica carta1=new CartaPolitica(new Colore("2"));
		CartaPolitica carta2=new CartaPolitica(new Colore("3"));
		carte.add(carta);
		carte.add(carta1);
		carte.add(carta2);
		Mazzo<CartaPolitica> mazzo=new Mazzo<CartaPolitica>(carte);
		
		assertEquals(carta, mazzo.pescaCarte());
	}

	@Test
	public void testMescolaCarte() {
		ArrayList<CartaPolitica> carte=new ArrayList<CartaPolitica>();
		CartaPolitica carta=new CartaPolitica(new Colore("1"));
		CartaPolitica carta1=new CartaPolitica(new Colore("2"));
		CartaPolitica carta2=new CartaPolitica(new Colore("3"));
		carte.add(carta);
		carte.add(carta1);
		carte.add(carta2);
		Mazzo<CartaPolitica> mazzo=new Mazzo<CartaPolitica>(carte);
		Mazzo<CartaPolitica> mazzo1=new Mazzo<CartaPolitica>(carte);
		mazzo.mescolaCarte();
		int i=-1;
		for(CartaPolitica c: carte){
			i++;
			if(c.equals(carte.get(i))){
				continue;
			}
			else{
				assertTrue(true);
				break;
			}
		}
	}

	@Test
	public void testAggiungiCarte() {
		ArrayList<CartaPolitica> carte=new ArrayList<CartaPolitica>();
		CartaPolitica carta1=new CartaPolitica(new Colore("1"));
		CartaPolitica carta2=new CartaPolitica(new Colore("2"));
		CartaPolitica carta3=new CartaPolitica(new Colore("3"));
		carte.add(carta1);
		carte.add(carta2);
		carte.add(carta3);
		Mazzo<CartaPolitica> m=new Mazzo<>();
		m.aggiungiCarte(carte);
		assertEquals(new Mazzo<>(carte).getCarte(), m.getCarte());
	}

}
