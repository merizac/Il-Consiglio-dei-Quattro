package it.polimi.ingsw.cg17;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
import bonus.BonusMoneta;
import bonus.BonusPuntiNobiltà;
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
	
	private static ArrayList<Consigliere> consiglieri=new ArrayList<Consigliere>();
	private static ArrayList<Regione> regioni=new ArrayList<Regione>(); 
	private static ArrayList<Città> cities=new ArrayList<Città>();

	
	public static PlanciaRe creazionePlanciaRe() throws IOException{
		ArrayList<Bonus> bonusRe = letturaBonusRe();
		Balcone balconeRe=new Balcone(4, consiglieri);
		PlanciaRe planciaRe = new PlanciaRe(balconeRe, bonusRe, letturaPunteggioNobiltà());
		
		return planciaRe;
	}
	
	public static Re creazioneRe(){
		Re re=new Re(findCittàRe());
		return re;
	}
	
	//costruiscre la mappa e mette le tessere permesso alle regioni
	public static Mappa creazioneMappa(String fileCittà) throws IOException{
		creazioneCittà(fileCittà);
		Mappa mappa=new Mappa(new HashSet<Città>(cities));
		letturaTesserePermesso(cities, regioni);
		return mappa;
	} 

	//crea città e gli mette i bonus rotondi
	public static void creazioneCittà(String fileCittà) throws IOException{
		cities=letturaCittà(fileCittà);
		letturaBonusTondiCittà();
		
	}
	
	public static Mazzo<CartaPolitica> letturaCartePolitica(String fileCartePolitica) throws IOException{
		
		ArrayList<CartaPolitica> cartaPoliticaList=new ArrayList<CartaPolitica>();
		FileReader cartaPolitica=new FileReader(fileCartePolitica);
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
		Collections.shuffle(cartaPoliticaList);
		return new Mazzo<CartaPolitica>(cartaPoliticaList);
	}
	
	public static ArrayList<PunteggioNobiltà> letturaPunteggioNobiltà() throws NumberFormatException, IOException{
		
		FileReader punteggioNobiltà=new FileReader("src/main/resources/punteggioNobiltà.txt");
		BufferedReader b;
		b=new BufferedReader(punteggioNobiltà);
		String stringaLetta;
		int lunghezza = Integer.parseInt(b.readLine());
		ArrayList<PunteggioNobiltà> nobiltà=new ArrayList<PunteggioNobiltà>(lunghezza);
				
		for(int i=0; i<lunghezza;i++){
			ArrayList<Bonus> bonus = new ArrayList<Bonus>();
			stringaLetta=b.readLine();
			
			if(!stringaLetta.equals("no")){
			StringTokenizer st=new StringTokenizer(stringaLetta);
			while(st.hasMoreTokens()){
				String tmp=st.nextToken();
				if(tmp.equals("BonusAiutanti")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusAiutanti(quantità));
				}
				else if(tmp.equals("BonusGettoneRicompensa")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusGettoneRicompensa(quantità));
				}
				else if(tmp.equals("BonusTesseraPermesso")){
					bonus.add(new BonusTesseraPermesso());
				}
				else if(tmp.equals("BonusTesseraPermessoUsata")){
					bonus.add(new BonusTesseraPermessoUsata());
				}
				else if(tmp.equals("BonusAzionePrincipale")){
					bonus.add(new BonusAzionePrincipale());
				}
				else if (tmp.equals("BonusPuntiVittoria")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusPuntiVittoria(quantità));
				}
				else if (tmp.equals("BonusCartePolitica")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusCartePolitica(quantità));
				}
				else if (tmp.equals("BonusMoneta")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusCartePolitica(quantità));
				}
			}
			}
			nobiltà.add(new PunteggioNobiltà(i, bonus));
		}
		b.close();
		return nobiltà;
	}
	
	public static ArrayList<Consigliere> letturaConsigliere() throws IOException{
		
		FileReader cons=new FileReader("src/main/resources/consiglieri.txt");
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
		Collections.shuffle(consiglieri);
		return consiglieri;
	}
	
	public static ArrayList<Regione> letturaRegioni() throws IOException{
		
		FileReader reg=new FileReader("src/main/resources/regioni.txt");
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
	     		int nbonus=0;
	     		if(st.hasMoreTokens())
	     			nbonus=Integer.parseInt(st.nextToken());
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

	
	public static ArrayList<Città> letturaCittà(String fileCittà) throws IOException{
	
		ArrayList<Colore> coloriCittà= new ArrayList<Colore>();
		FileReader città=new FileReader(fileCittà);
		BufferedReader b;
		b=new BufferedReader(città);
		String stringaLetta;
		stringaLetta=b.readLine();

		//Creo coloricittà salvati in un arraylist
		while(!stringaLetta.equals("CITTA")){
			StringTokenizer st=new StringTokenizer(stringaLetta);
	     	String colore=st.nextToken();
	    	ColoreCittà colorecittà=null;
	    	ColoreRe coloreRe=null;
	   		int puntiBonus=0;
	   		if(!colore.equals("Re")){
	   			if(st.hasMoreTokens()){
     			puntiBonus=Integer.parseInt(st.nextToken());
	     		colorecittà=new ColoreCittà(colore, new BonusPuntiVittoria(puntiBonus));
	     		coloriCittà.add(colorecittà);
	     		}
	   		}
	     	else {
	   			coloreRe=new ColoreRe(colore);
	   			coloriCittà.add(coloreRe);
    		}	     	
	     	stringaLetta=b.readLine();
		}
		
		//Ciclo le regioni poi i colori e setto la città
		for(Regione regione: regioni){
			int numerocittà=Integer.parseInt(b.readLine());
			for(int i=0; i<numerocittà;i++){
				String tmp=b.readLine();
				StringTokenizer st=new StringTokenizer(tmp);
				while(st.hasMoreTokens()){
				String nome=st.nextToken();
				String col=st.nextToken();
				for(Colore color: coloriCittà){
					if(col.equals(color.getColore())){
						if(!col.equals("Re")){
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
			
		}
		//città collegate
		if (b.readLine().equals("CITTACOLLEGATE")){
			for(Città c:cities){
				StringTokenizer st=new StringTokenizer(b.readLine());
				if(st.hasMoreTokens()){
				String nome=st.nextToken();
				if(nome.equals(c.getNome())){
					while(st.hasMoreTokens()){
						String cittàCollegata=st.nextToken();
						Città cittàToFind=findCittà(cittàCollegata);
						c.getCittàCollegate().add(cittàToFind);
					}
				}
				else
					continue;
			}
			}
		}
		
		b.close();
		return cities;
	}
	

	public static ArrayList<Bonus> letturaBonusRe() throws IOException{

		ArrayList<Bonus> bonusRe=new ArrayList<>();
		
		FileReader bonus=new FileReader("src/main/resources/bonusRe.txt");
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

	
	public static void letturaBonusTondiCittà() throws IOException{
		
		List<ArrayList<Bonus>> listaBonusTondi= new ArrayList<ArrayList<Bonus>>();
		FileReader bonusDelleCittà=new FileReader("src/main/resources/bonusDelleCittà.txt");
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
	     		
	     		if(nomeBonus.equals("BonusCartaPolitica")){
	     			int quantità=Integer.parseInt(st.nextToken());
	     			bonus.add(new BonusCartePolitica(quantità));
	     		}
	     		else if (nomeBonus.equals("BonusMoneta")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusMoneta(quantità));
				}
	     		else if (nomeBonus.equals("BonusPuntiNobiltà")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusPuntiNobiltà(quantità));
				}
	     		else if (nomeBonus.equals("BonusAiutanti")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusAiutanti(quantità));
				}
	     		else if (nomeBonus.equals("BonusPuntiVittoria")){
					int quantità=Integer.parseInt(st.nextToken());
					bonus.add(new BonusPuntiVittoria(quantità));
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

	
	public static void letturaTesserePermesso(ArrayList<Città> cities, ArrayList<Regione> regioni) throws IOException{
		
		FileReader t=new FileReader("src/main/resources/tesseraPermesso.txt");
		BufferedReader b;
		b=new BufferedReader(t);
		String stringaLetta;
		stringaLetta=b.readLine();
		
		while(true) {	
	     	if(stringaLetta==null)
	     		break;
			for (Regione r: regioni){
		     	while(!stringaLetta.equals("FINEREGIONE")){
			     	StringTokenizer st=new StringTokenizer(stringaLetta);
			     	ArrayList<Città> cit=new ArrayList<>();
			     	//aggiunge le citta all'arraylist
			     	while(st.hasMoreTokens()){
			     		cit.add(findCittà(st.nextToken()));		     	
			     	}
			     	
			     	//aggiunge i bonus all'arraylist
			     	stringaLetta=b.readLine();
			     	ArrayList<Bonus> bonus = new ArrayList<Bonus>();
			     	StringTokenizer str=new StringTokenizer(stringaLetta);
					
			     	String tmp=str.nextToken();
					while(true){
						if(tmp.equals("BonusAiutanti")){
							int quantità=Integer.parseInt(str.nextToken());
							bonus.add(new BonusAiutanti(quantità));
						}
						else if(tmp.equals("BonusAzionePrincipale")){
							bonus.add(new BonusAzionePrincipale());
						}
						else if(tmp.equals("BonusMoneta")){
								int quantità=Integer.parseInt(str.nextToken());
								bonus.add(new BonusMoneta(quantità));
						}
						else if (tmp.equals("BonusPuntiVittoria")){
								int quantità=Integer.parseInt(str.nextToken());
								bonus.add(new BonusPuntiVittoria(quantità));
						}
						else if (tmp.equals("BonusCartePolitica")){
								int quantità=Integer.parseInt(str.nextToken());
								bonus.add(new BonusCartePolitica(quantità));
						}
						else if (tmp.equals("BonusPuntiNobiltà")){
								int quantità=Integer.parseInt(str.nextToken());
								bonus.add(new BonusPuntiNobiltà(quantità));
						}
						if(str.hasMoreTokens())
							tmp=str.nextToken();
						else 
							break;
					}
			     	
					new TesseraPermesso(cit, bonus, r);	
			    	stringaLetta=b.readLine();
					}
		     	stringaLetta=b.readLine();
		     	r.getTesserePermessoScoperte().add(r.getMazzoTesserePermesso().pescaCarte());
		     	r.getTesserePermessoScoperte().add(r.getMazzoTesserePermesso().pescaCarte());
		     	}
			}
		b.close();
	}
	
	
	public static Città findCittà(String c){
		for (Città i: cities){
			if(i.getNome().equals(c)){
				
				return i;
			}
		}
		return null;
	}

	
	//manca eccezione
	public Regione findRegione(String c){
		for (Regione i: regioni){
			if(i.getNome().equals(c)){
				
				return i;
			}
		}
		return null;
	}

	
	public static Città findCittàRe(){
		for(Città c:cities){
			if(c.getColoreCittà().getColore().equals("Re")){
				return c;
			}
		}
		return null;
	}
	
}