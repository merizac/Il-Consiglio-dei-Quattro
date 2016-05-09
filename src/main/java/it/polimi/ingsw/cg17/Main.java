package it.polimi.ingsw.cg17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import bonus.Bonus;
import bonus.BonusAiutanti;
import bonus.BonusCartePolitica;
import bonus.BonusPuntiNobiltà;
import bonus.BonusPuntiVittoria;
import game.Aiutante;
import game.Balcone;
import game.CartaPolitica;
import game.Città;
import game.Colore;
import game.ColoreCittà;
import game.Consigliere;
import game.Emporio;
import game.Giocatore;
import game.Mappa;
import game.Mazzo;
import game.PlanciaRe;
import game.PunteggioNobiltà;
import game.Re;
import game.Regione;
import game.Tabellone;
import game.TesseraPermesso;

public class Main {

	public static void main(String[] args) {		
		//creo lista dei possibili card colour
		ArrayList<CartaPolitica> cartaPoliticaLista=new ArrayList<CartaPolitica>();
				for(int i=0; i<10; i++){
					CartaPolitica CartaPolitica1=new CartaPolitica(new Colore("White"));
					cartaPoliticaLista.add(CartaPolitica1);
					CartaPolitica CartaPolitica2=new CartaPolitica(new Colore("Black"));
					cartaPoliticaLista.add(CartaPolitica2);
					CartaPolitica CartaPolitica3=new CartaPolitica(new Colore("Orange"));
					cartaPoliticaLista.add(CartaPolitica3);
					CartaPolitica CartaPolitica4=new CartaPolitica(new Colore("Pink"));
					cartaPoliticaLista.add(CartaPolitica4);
					CartaPolitica CartaPolitica5=new CartaPolitica(new Colore("Purple"));
					cartaPoliticaLista.add(CartaPolitica5);
					CartaPolitica CartaPolitica6=new CartaPolitica(new Colore("Violet"));
					cartaPoliticaLista.add(CartaPolitica6);
					CartaPolitica CartaPolitica7=new CartaPolitica(new Colore("multicolore"));
					cartaPoliticaLista.add(CartaPolitica7);
				}
		//creo politic deck
		Mazzo<CartaPolitica> mazzoCartePolitica= new Mazzo<CartaPolitica>(cartaPoliticaLista);
		mazzoCartePolitica.mescolaCarte();
		ArrayList<Bonus> bonus = new ArrayList<Bonus>();
		
		
		//creo ArrayList<PunteggioNobiltà> con bonus main action in casella 1
			ArrayList<PunteggioNobiltà> nobilityTrack=new ArrayList<PunteggioNobiltà>(20);
			for(int i=0; i<nobilityTrack.size(); i++)
			{
				nobilityTrack.add(new PunteggioNobiltà(i, bonus));
			}
		//crea empori giocatori	
		ArrayList<Emporio> giocatore1 = new ArrayList<>(); 
		ArrayList<Emporio> giocatore2 = new ArrayList<>(); 
		ArrayList<Emporio> giocatore3 = new ArrayList<>(); 
		
		for(int i=0; i<10; i++){
			giocatore1.add(new Emporio(new Colore("giallo")));
			giocatore2.add(new Emporio(new Colore("verde")));
			giocatore3.add(new Emporio(new Colore("blu")));
		}
		
		//creo carte politica giocatori
		
		ArrayList<CartaPolitica> cartaPolitica1=new ArrayList<>();
		ArrayList<CartaPolitica> cartaPolitica2=new ArrayList<>();
		ArrayList<CartaPolitica> cartaPolitica3=new ArrayList<>();
		
		for(int i=0; i<10; i++){
			cartaPolitica1.add(mazzoCartePolitica.pescaCarte());
			cartaPolitica2.add(mazzoCartePolitica.pescaCarte());
			cartaPolitica3.add(mazzoCartePolitica.pescaCarte());
			
		}
		
		//creo giocatori e li metto in una lista
		List<Giocatore> Giocatore=new ArrayList<Giocatore>();
		Giocatore Giocatore1=new Giocatore(new Colore("giallo"),cartaPolitica1,new Aiutante(3),0,8, nobilityTrack.get(0), giocatore1 );
		Giocatore.add(Giocatore1);
		Giocatore Giocatore2=new Giocatore(new Colore("verde"),cartaPolitica2,new Aiutante(3),0,9, nobilityTrack.get(0), giocatore2);
		Giocatore.add(Giocatore2);
		Giocatore Giocatore3=new Giocatore(new Colore("blu"),cartaPolitica3,new Aiutante(3),0,10, nobilityTrack.get(0), giocatore3 );
		Giocatore.add(Giocatore3);
		
		//creo i possibili city colour
		ColoreCittà Citycolor1=new ColoreCittà("Gold", new BonusPuntiVittoria(20));
		ColoreCittà Citycolor2=new ColoreCittà("Silver", new BonusPuntiVittoria(12));
		ColoreCittà Citycolor3=new ColoreCittà("Bronze", new BonusPuntiVittoria(8));
		ColoreCittà Citycolor4=new ColoreCittà("Blue", new BonusPuntiVittoria(5));
		
		
		
		//instanzio tutti i consiglieri del gioco partendo dalla lista di card colour
		//e li metto in una lista
		ArrayList<Consigliere> councillorsList=new ArrayList<Consigliere>();
		for(CartaPolitica CartaPolitica : cartaPoliticaLista)
			if(!CartaPolitica.equals("multicolore"))
				for(int i=0;i<6;i++)
					councillorsList.add(new Consigliere(CartaPolitica.getColore()));
		Collections.shuffle(councillorsList);
		
		//creo la riserva consiglieri passando al costruttore la lista di tutti i consiglieri
		ArrayList<Consigliere> councillorsReserve=new ArrayList<Consigliere>(councillorsList);
				
		//creo i 4 balconi rimuovendo 4 consiglieri alla volta dalla lista
		Balcone seaBalcony=new Balcone(4, councillorsReserve);
		Balcone hillBalcony=new Balcone(4, councillorsReserve);
		Balcone mountainBalcony=new Balcone(4, councillorsReserve);
		Balcone kingBalcony=new Balcone(4, councillorsReserve);
		
		
		
		//creo permit deck vuoti
		Mazzo<TesseraPermesso> seaPermitDeck=new Mazzo<TesseraPermesso>();
		Mazzo<TesseraPermesso> hillPermitDeck=new Mazzo<TesseraPermesso>();
		Mazzo<TesseraPermesso> mountainPermitDeck=new Mazzo<TesseraPermesso>();
		
		//creo le regioni
		//N.B. passo al costruttore deck vuoti perchè non posso ancora creare le città
		Regione seaRegion=
				new Regione("Sea", seaPermitDeck, new BonusPuntiVittoria(5) ,seaBalcony);
		Regione hillRegion=
				new Regione("Hill", hillPermitDeck,new BonusPuntiVittoria(5), hillBalcony);
		Regione mountainRegion=
				new Regione("Mountain", mountainPermitDeck,new BonusPuntiVittoria(5), mountainBalcony);
		
		//raggruppo regioni in una lista
		ArrayList<Regione> regionList =new ArrayList<Regione>();
		regionList.add(seaRegion);
		regionList.add(hillRegion);
		regionList.add(mountainRegion);
		
		//creo città e le divido in set per regioni
		ArrayList<Città> seaSet=new ArrayList<Città>();
		Città a=new Città("Arkon", seaRegion, Citycolor4);
		Città b=new Città("Burgen", seaRegion, Citycolor1);
		Città c=new Città("Castrum", seaRegion, Citycolor2);
		Città d=new Città("Dorful", seaRegion, Citycolor2);
		Città e=new Città("Esti", seaRegion, Citycolor3);
		seaSet.add(a);
		seaSet.add(b);
		seaSet.add(c);
		seaSet.add(d);
		seaSet.add(e);
		
		ArrayList<Città> hillSet=new ArrayList<Città>();
		Città f=new Città("Framek", hillRegion, Citycolor1);
		Città g=new Città("Graden", hillRegion, Citycolor2);
		Città h=new Città("Hellar", hillRegion, Citycolor1);
		Città i=new Città("Indur", hillRegion, Citycolor3);
		Città j=new Città("Juvelar", hillRegion, Citycolor4); 
		hillSet.add(f);
		hillSet.add(g);
		hillSet.add(h);
		hillSet.add(i);
		hillSet.add(j);
		
		ArrayList<Città> mountainSet=new ArrayList<Città>();
		Città k=new Città("Kultos", mountainRegion, Citycolor1);
		Città l=new Città("Lyram", mountainRegion, Citycolor2);
		Città m=new Città("Merkatim", mountainRegion, Citycolor4);
		Città n=new Città("Naris", mountainRegion, Citycolor3);
		Città o=new Città("Osium", mountainRegion, Citycolor1);
		mountainSet.add(k);
		mountainSet.add(l);
		mountainSet.add(m);
		mountainSet.add(n);
		mountainSet.add(o);
		

		//creo permit tiles. 
		//Tutte le permit tile permettono di costruire in tutte le città della regione
		//Tutte le permit tile di una regione hanno lo stesso bonus
		//Di conseguenza il deck di una regione sarà composto da 10 tile uguali
		List<TesseraPermesso> permitSeaList = new ArrayList<TesseraPermesso>();
		for(int ind=0;ind<10;ind++){
			permitSeaList.add(new TesseraPermesso(seaSet, new ArrayList<Bonus>(), seaRegion));
			permitSeaList.get(ind).getBonus().add(new BonusPuntiNobiltà(1));
		}
		
		List<TesseraPermesso> permitHillList = new ArrayList<TesseraPermesso>();
		for(int ind=0;ind<10;ind++){
			permitHillList.add(new TesseraPermesso(hillSet, new ArrayList<Bonus>(), hillRegion));
			permitHillList.get(ind).getBonus().add(new BonusAiutanti(1));
		}
		
		List<TesseraPermesso> permitMountainList = new ArrayList<TesseraPermesso>();
		for(int ind=0;ind<10;ind++){
			permitMountainList.add(new TesseraPermesso(mountainSet, new ArrayList<Bonus>(), mountainRegion));
			permitMountainList.get(ind).getBonus().add(new BonusCartePolitica(2));
		}
		
		//aggiungo collegamenti tra le città
		a.getCittàCollegate().add(c);
		a.getCittàCollegate().add(b);
		b.getCittàCollegate().add(d);
		b.getCittàCollegate().add(e);
		c.getCittàCollegate().add(f);
		d.getCittàCollegate().add(g);
		e.getCittàCollegate().add(h);
		f.getCittàCollegate().add(i);
		g.getCittàCollegate().add(i);
		h.getCittàCollegate().add(j);
		h.getCittàCollegate().add(j);
		i.getCittàCollegate().add(k);
		i.getCittàCollegate().add(j);
		j.getCittàCollegate().add(l);
		k.getCittàCollegate().add(n);
		l.getCittàCollegate().add(o);
		m.getCittàCollegate().add(o);
		
		//creo mappa
		HashSet<Città> cities=new HashSet<Città>(seaSet);
		cities.addAll(hillSet);
		cities.addAll(mountainSet);
		
		Mappa mappa=new Mappa(cities);
		
		
		//
		Re re= new Re(j);
		ArrayList<Bonus> bonusRe=new ArrayList<>();
		bonusRe.add(new BonusPuntiVittoria(25));
		bonusRe.add(new BonusPuntiVittoria(18));
		bonusRe.add(new BonusPuntiVittoria(12));
		bonusRe.add(new BonusPuntiVittoria(7));
		bonusRe.add(new BonusPuntiVittoria(3));
		
		
		PlanciaRe planciaRe = new PlanciaRe(kingBalcony, bonusRe, nobilityTrack); 
		
		//creo game table
		Tabellone gameTable=
				new Tabellone(mappa, regionList, planciaRe, re, councillorsList, mazzoCartePolitica);
		
		//creo game
		/*Partita game=new Partita(Giocatore, gameTable);
		
		GameLogic gameLogic=new GameLogic(game);
		View view=new ViewCLI(gameLogic);
		view.registerObserver(gameLogic);
		
		gameLogic.play();
		
		
//		System.out.println(game);*/				
	}

}
