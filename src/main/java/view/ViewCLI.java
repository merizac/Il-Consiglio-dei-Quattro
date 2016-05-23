package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import game.Città;
import game.Consigliere;
import game.GameState;
import game.Regione;
import game.notify.Notify;

public class ViewCLI extends View{
	
	private ArrayList<String> pattern=new ArrayList<>();

	@Override
	public void update(Notify c) {
		// TODO Auto-generated method stub
		
	}


	/*public ViewCLI(GameState gameState, ParserAzione parser) {
		super(gameState, parser);
=======
	public ViewCLI(GameState gameState, ParserAzione parser) {
		//super(gameState, parser);
>>>>>>> branch 'fixMvc' of https://nicolasosio@bitbucket.org/nicolasosio/progettoingsoftware.git
		createPattern();
	}
	
	public ViewCLI(GameState gameState) {
		super(gameState);
		// TODO Auto-generated constructor stub
	}
	
	

	private void createPattern() {

		String colori="";

		String colori= "(";
>>>>>>> branch 'fixMvc' of https://nicolasosio@bitbucket.org/nicolasosio/progettoingsoftware.git
		Set<Consigliere> consiglieri= new HashSet<>(model.getConsiglieri());
		for(Consigliere c: consiglieri){
			colori=colori+c.getColore().getColore().toString()+"|";
		}
		String cartePolitica=colori+"|Multicolore";
				
		String regioni="";
		for(Regione r: model.getRegioni()){
			regioni=regioni+r.toString()+"|";
		}
		
		String città="";
		for(Città c: model.getCittà()){
			città=città+c.getNome().toString()+"|";
		}
		
		String indiceTessera="((\\d)*( ))*";
		String atp="(1p) ("+regioni+") (("+cartePolitica+") ){1,4}"+"([1-2])";
		String cr="(2p) ("+città+") ("+cartePolitica+")";
		String ctp="(3p) ("+indiceTessera+")("+città+")";
		String ec="(4p) ("+regioni+") ("+colori+")";
		String ct="(1v) ("+regioni+")";
		String ecv="(2v) ("+regioni+") ("+colori+")";
		String ia="(3v) ";
		String sap="(4v)";
<<<<<<< HEAD
		/*Pattern p=Pattern.compile(atp);
		System.out.println(p);
		pattern.add(p);
		System.out.println(pattern.get(0));
		
		pattern.add(atp);
		pattern.add(cr);
		pattern.add(ctp);
		pattern.add(ec);
		pattern.add(ct);
		pattern.add(ecv);
		pattern.add(ia);
		pattern.add(sap);
		System.out.println(pattern.get(0));
		System.out.println(pattern.get(1));
		System.out.println(pattern.get(2));
		System.out.println(pattern.get(3));
		System.out.println(pattern.get(4));
		System.out.println(pattern.get(5));
		System.out.println(pattern.get(6));
		System.out.println(pattern.get(7));
		
		
		
		//pattern= Pattern.compile("("+atp+"|"+cr+"|"+ctp+"|"+ec+"|"+ct+"|"+ecv+"|"+ia+"|"+sap+")");
		
	}
	
	public boolean match(String input){
		for(String regex: pattern){
			Pattern p=Pattern.compile(regex);
			Matcher m = p.matcher(input);
			if(m.matches())
				return true;
		}
		return false;
		

		pattern= Pattern.compile("("+atp+"|"+cr+"|"+ctp+"|"+ec+"|"+ct+"|"+ecv+"|"+ia+"|"+sap+")");
		

	}
	
	@Override	
	public void input(Scanner scanner) {
		/*System.out.println("Inserisci numero giocatori");
		String input=scanner.nextLine();
		while(!isNumeric(input))
			System.out.println("valore non valido: inserisci numero giocatori");

		parser.instanziaGiocatori(input);

		//parser.instanziaGiocatori(input);

		
		
	}
	

	/*private boolean isNumeric(String input) {
		try{
			Integer.parseInt(input);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}

	@Override
	public void update(Notify notify) {

		notify.stamp(this);
	}
	
	public static void main(String[] args){
		try {
			GameState gameState=new GameState();
			ViewCLI viewCLI=new ViewCLI(gameState);
			viewCLI.createPattern();
			Scanner scanner=new Scanner(System.in);
			while(true){
				System.out.println("Inserisci azione");
				String azione=scanner.nextLine();
				System.out.println(viewCLI.match(azione));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		//notify.stamp(this);

	}

	@Override
	public void update(Notify c) {
		// TODO Auto-generated method stub
		
	}*/
	

}
