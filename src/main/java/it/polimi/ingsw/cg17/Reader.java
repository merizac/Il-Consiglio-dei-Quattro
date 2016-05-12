package it.polimi.ingsw.cg17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import bonus.Bonus;
import bonus.BonusAiutanti;
import bonus.BonusAzionePrincipale;
import bonus.BonusCartePolitica;
import bonus.BonusGettoneRicompensa;
import bonus.BonusPuntiVittoria;
import bonus.BonusTesseraPermesso;
import bonus.BonusTesseraPermessoUsata;
import game.Balcone;
import game.CartaPolitica;
import game.Colore;
import game.ColoreCittà;
import game.ColoreRe;
import game.Consigliere;
import game.GameState;
import game.Mappa;
import game.Regione;
import game.TesseraPermesso;
import game.Mazzo;
import game.PlanciaRe;
import game.PunteggioNobiltà;
import game.Re;
import game.Città;
import game.CittàBonus;

public class Reader {

	public void inizializzatore() throws IOException{

		//MazzoCartePolitica
		Mazzo<CartaPolitica> cartePolitica=letturaCartePolitica();
		//ArrayListPunteggioNobiltà
		ArrayList<PunteggioNobiltà> punteggioNobiltà=letturaPunteggioNobiltà();
		
		//ArrayListEmporiVuoto
		//ArrayLisCartePoliticaGiocatoriVuoto
		//ciclifor riempiono tutto
		//ArrayListGiocatori
		/*int numGiocatori=3;
		List<Giocatore> giocatori=new ArrayList<Giocatore>();
		for(int i=0;i<numGiocatori;i++){
			String colore=Integer.toString(i);
			Colore color=new Colore(colore);
			ArrayList<Emporio> emporiGiocatore = new ArrayList<>();
			ArrayList<CartaPolitica> cartePoliticaGiocatore=new ArrayList<>();
			for(int j=0; j<10; j++){
				emporiGiocatore.add(new Emporio(color));
			}
			for(int k=0; k<4; k++){
				cartePoliticaGiocatore.add(cartePolitica.pescaCarte());
			}
			Giocatore giocatore = new Giocatore(color, cartePoliticaGiocatore, new Aiutante(3), 0, 5+i, punteggioNobiltà.get(0), emporiGiocatore);
			giocatori.add(giocatore);
		}*/
		
		//ArrayListConsiglieri
		ArrayList<Consigliere> consiglieri = letturaConsigliere();
		Collections.shuffle(consiglieri);
		//ArrayListRegione
		ArrayList<Regione> regioni=letturaRegioni(consiglieri);
		
		//CREAZIONE CITTA e tutto ciò che comportano
		ArrayList<Città> cities=letturaCittà(regioni);
		
		//deve leggere da file la pripria città
		Re re=new Re(findCittà("Re", cities)); //NON FUNZIONNAAAAAAAAAAAAAAA DEVO CECARE CON IL COLORE
	
		
		
		
		
		ArrayList<Bonus> bonusRe = letturaBonusRe();
		Balcone balconeRe=new Balcone(4, consiglieri);
		PlanciaRe planciaRe = new PlanciaRe(balconeRe, bonusRe, punteggioNobiltà); 
		
		//TesserePermesso
		FileReader t=new FileReader("tesseraPermesso.txt");
		BufferedReader b;
		b=new BufferedReader(t);
		String stringaLetta;
		
		while(true) {	
			stringaLetta=b.readLine();
	     	if(stringaLetta.equals("FINE"))
	     		break;
			for (Regione r: regioni){
		     	while(stringaLetta!="FINEREGIONE"){
					//if(stringaLetta=="FINEREGIONE")
			     	//	break;
			     	//aggiunge le città ad un arraylist
			     	ArrayList<Città> cit=new ArrayList<>();;
			     	StringTokenizer st=new StringTokenizer(stringaLetta);
			     	while(st.hasMoreTokens()){
			     		//c=new ArrayList<>();
			     		cit.add(findCittà(st.nextToken(), cities));		     	
			     	}
			     	
			     	//aggiunge i bonus
			     	stringaLetta=b.readLine();
			     	StringTokenizer str=new StringTokenizer(stringaLetta);
			     	ArrayList<Bonus> bonus = new ArrayList<Bonus>();
			     	while(str.hasMoreTokens()){    	
						String tmp=str.nextToken();
						if(tmp.equals("BonusAiutanti")){
							int quantità=Integer.parseInt(st.nextToken());
							bonus.add(new BonusAiutanti(quantità));
							continue;
						}
						if(tmp.equals("BonusCartePolitica")){
							int quantità=Integer.parseInt(st.nextToken());
							bonus.add(new BonusCartePolitica(quantità));
							continue;
						}
						if(tmp.equals("BonusTesseraPermesso")){
							bonus.add(new BonusTesseraPermesso());
							continue;
						}
						if(tmp.equals("BonusTesseraPermessoUsata")){
							bonus.add(new BonusTesseraPermessoUsata());
							continue;
						}
						if(tmp.equals("BonusAzionePrincipale")){
							bonus.add(new BonusAzionePrincipale());
							continue;
						}
						if(tmp.equals("BonusPuntiVittoria")){
							int quantità=Integer.parseInt(st.nextToken());
							bonus.add(new BonusPuntiVittoria(quantità));
							continue;
						}
					}
		     	TesseraPermesso tesseraPermesso=new TesseraPermesso(cit, bonus, r);	
		     	stringaLetta=b.readLine();
		     	}
			}
			b.close();
		}
		
		
		//Mappa mappa=new Mappa(creaHashCittà(cities));
		Mappa mappa=new Mappa(new HashSet<Città>(cities));
		
		GameState tabellone=new GameState(mappa, regioni, planciaRe, re, consiglieri, cartePolitica);
		
	}

	public Mazzo<CartaPolitica> letturaCartePolitica() throws IOException{
		
		ArrayList<CartaPolitica> cartaPoliticaList=new ArrayList<CartaPolitica>();
		FileReader cartaPolitica=new FileReader("cartaPolitica.txt");
		BufferedReader b;
		b=new BufferedReader(cartaPolitica);
		String stringaLetta;

		while(true) {
	    	stringaLetta=b.readLine();
	     	if(stringaLetta==null)
	     	break;
	     	CartaPolitica carta =new CartaPolitica(new Colore(stringaLetta));
	     	cartaPoliticaList.add(carta);
		}
		b.close();
		return new Mazzo<CartaPolitica>(cartaPoliticaList);
	}
	
	public ArrayList<PunteggioNobiltà> letturaPunteggioNobiltà() throws NumberFormatException, IOException{
		
		FileReader punteggioNobiltà=new FileReader("punteggioNobiltà.txt");
		BufferedReader b;
		b=new BufferedReader(punteggioNobiltà);
		String stringaLetta;
		int lunghezza = Integer.parseInt(b.readLine());
		ArrayList<PunteggioNobiltà> nobiltà=new ArrayList<PunteggioNobiltà>(lunghezza);
				
		for(int i=0; i<lunghezza;i++){
			ArrayList<Bonus> bonus = new ArrayList<Bonus>();
			stringaLetta=b.readLine();
			
			if(stringaLetta!="no"){
			StringTokenizer st=new StringTokenizer(stringaLetta);
			while(st.hasMoreTokens()){
				String tmp=st.nextToken();
				if(tmp.equals("BonusAiutanti")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusAiutanti(quantità));
					continue;
				}
				if(tmp.equals("BonusGettoneRicompensa")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusGettoneRicompensa(quantità));
					continue;
				}
				if(tmp.equals("BonusTesseraPermesso")){
					bonus.add(new BonusTesseraPermesso());
					continue;
				}
				if(tmp.equals("BonusTesseraPermessoUsata")){
					bonus.add(new BonusTesseraPermessoUsata());
					continue;
				}
				if(tmp.equals("BonusAzionePrincipale")){
					bonus.add(new BonusAzionePrincipale());
					continue;
				}
				if(tmp.equals("BonusPuntiVittoria")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusPuntiVittoria(quantità));
					continue;
				}
			}
			nobiltà.add(new PunteggioNobiltà(i, bonus));
			}
		}
		b.close();
		return nobiltà;
	}
	
	public ArrayList<Consigliere> letturaConsigliere() throws IOException{
		
		ArrayList<Consigliere> consiglieri=new ArrayList<Consigliere>();
		FileReader cons=new FileReader("consigieri.txt");
		BufferedReader b;
		b=new BufferedReader(cons);
		String stringaLetta;

		while(true) {
	    	stringaLetta=b.readLine();
	     	if(stringaLetta==null)
	     		break;
	     	Consigliere consigliere =new Consigliere(new Colore(stringaLetta));
	     	consiglieri.add(consigliere);
		}
		b.close();
		
		return consiglieri;
	}
	
	public ArrayList<Regione> letturaRegioni(ArrayList<Consigliere> consiglieri) throws IOException{
		
		ArrayList<Regione> regioni=new ArrayList<Regione>();
		FileReader reg=new FileReader("regioni.txt");
		BufferedReader b;
		b=new BufferedReader(reg);
		String stringaLetta;

		while(true) {
	    	stringaLetta=b.readLine();
	     	if(stringaLetta==null)
	     		break;
	     	StringTokenizer st=new StringTokenizer(stringaLetta);
	     	while(st.hasMoreTokens()){
	     		String nomeregione=st.nextToken();
	     		int nbonus=Integer.parseInt(st.nextToken());
	     		Mazzo<TesseraPermesso> mazzo=new Mazzo<TesseraPermesso>();
		     	Balcone balcone=new Balcone(4, consiglieri);
		     	BonusPuntiVittoria bonusPuntiVittoria=new BonusPuntiVittoria(nbonus);
		     	Regione regione=new Regione(nomeregione, mazzo, bonusPuntiVittoria, balcone);
		     	regioni.add(regione);
	     	}
		}
		b.close();

		return regioni;
	}

	public ArrayList<Città> letturaCittà(ArrayList<Regione> regioni) throws IOException{
	
		ArrayList<Colore> coloriCittà= new ArrayList<Colore>();
		ArrayList<Città> cities = new ArrayList<Città>();
		FileReader città=new FileReader("città.txt");
		BufferedReader b;
		b=new BufferedReader(città);
		String stringaLetta;
		stringaLetta=b.readLine();

		//Creo coloricittà salvati in un arraylist
		while(stringaLetta!="CITTA"){

			StringTokenizer st=new StringTokenizer(stringaLetta);
	     	//while(st.hasMoreTokens()){
     		String colore=st.nextToken();
     		ColoreCittà colorecittà;
     		ColoreRe coloreRe;
     		if(colore!="Re"){
     			colorecittà=new ColoreCittà(colore, new BonusPuntiVittoria(Integer.parseInt(st.nextToken())));
     			coloriCittà.add(colorecittà);
     		}
     		 else {
     			coloreRe=new ColoreRe(colore);
     			coloriCittà.add(coloreRe);
     		 }
     		//}
	     	stringaLetta=b.readLine();
		}
		
		//Ciclo le regioni poi i colori e setto la città
		for(Regione regione: regioni){
			int numerocittà=Integer.parseInt(b.readLine());
			for(int i=0; i<numerocittà;i++){
				String tmp=b.readLine();
				StringTokenizer st=new StringTokenizer(tmp);
				String nome=st.nextToken();
				String col=st.nextToken();
				for(Colore color: coloriCittà){
					if(col.equals(color.getColore())){
						if(col!="Re"){
						ArrayList<Bonus> bonus=new ArrayList<>();
						ColoreCittà colore=(ColoreCittà)color;
						CittàBonus c=new CittàBonus(nome, regione, colore, bonus);
						cities.add(c);
						}
						else{
							ColoreRe colore=(ColoreRe)color;
							Città c=new Città(nome, regione, colore);
							cities.add(c);
						}
					}
					
				}
			}
		}
			
		//città collegate
		if (b.readLine()=="CITTACOLLEGATE"){
			for(Città c:cities){
				StringTokenizer st=new StringTokenizer(b.readLine());
				String nome=st.nextToken();
				if(nome.equals(c.getNome())){
					while(st.hasMoreTokens())
						c.getCittàCollegate().add(findCittà(st.nextToken(), cities));
				}
				else
					continue;
			}
		}
		
		b.close();
		
		return cities;
	}
	
	public ArrayList<Bonus> letturaBonusRe() throws IOException{

		ArrayList<Bonus> bonusRe=new ArrayList<>();
		
		FileReader bonus=new FileReader("bonusRe.txt");
		BufferedReader b;
		b=new BufferedReader(bonus);
		
		while(true) {
			String letta=b.readLine();
	     	if(letta==null)
	     	break;
	    	bonusRe.add(new BonusPuntiVittoria(Integer.parseInt(letta)));
		}
		b.close();
				
		return bonusRe;
	}

	public void letturaBonusTondiCittà(ArrayList<Città> cities) throws IOException{
		
		List<ArrayList<Bonus>> listaBonusTondi= new ArrayList<ArrayList<Bonus>>();
		FileReader bonusDelleCittà=new FileReader("bonusDelleCittà.txt");
		BufferedReader b;
		b=new BufferedReader(bonusDelleCittà);
		String stringaLetta;

		while(true) {
//legge riga e controlla ci sia scritto qualcosa			
	    	stringaLetta=b.readLine();								
	     	if(stringaLetta==null)									
	     		break;
	     	
//spezzo la riga letta in token e leggo nome bonus e intero per i bonus di cui ne ho bisogno
	     	StringTokenizer st=new StringTokenizer(stringaLetta);
	     	ArrayList<Bonus> bonus=new ArrayList<>();
	     
	     	while(st.hasMoreTokens()){
	     		
	     		String nomeBonus=st.nextToken();
	     		
	     		if(nomeBonus.equals("BonusAiutante")){
	     			int quantità=Integer.parseInt(st.nextToken());
	     			bonus.add(new BonusAiutanti(quantità));
	     			continue;
	     		}
	     		
	     		
	     		
	     		
	     		
	     		
	
	     	}
	     	listaBonusTondi.add(bonus);
	     	
		}
		b.close();
		
		Collections.shuffle(listaBonusTondi);

		for(Città c:cities){
			if(c instanceof CittàBonus){
				((CittàBonus) c).setBonus(listaBonusTondi.remove(0) );
			}
		
		
		}
	}

	public Città findCittà(String c, ArrayList<Città> elenco){
		for (Città i: elenco){
			if(i.getNome().equals(c)){
				
				return i;
			}
		}
		return null;
	}

	
	//manca eccezione
	public Regione findRegione(String c, ArrayList<Regione> elenco){
		for (Regione i: elenco){
			if(i.getNome().equals(c)){
				
				return i;
			}
		}
		return null;
	}

	public HashSet<Città> creaHashCittà(ArrayList<Città> città){
		HashSet<Città> cities=new HashSet<Città>(città);
		return cities;
	}

}