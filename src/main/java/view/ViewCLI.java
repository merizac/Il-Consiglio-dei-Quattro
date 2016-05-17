package view;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import game.Città;
import game.Consigliere;
import game.GameState;
import game.ParserAzione;
import game.Regione;
import game.azioni.Azione;
import game.notify.Notify;

public class ViewCLI extends View{
	
	private Pattern pattern;

	public ViewCLI(GameState gameState, ParserAzione parser) {
		super(gameState, parser);
		createPattern();
	}

	private void createPattern() {
		String colori= "(";
		Set<Consigliere> consiglieri= new HashSet<>(model.getConsiglieri());
		for(Consigliere c: consiglieri){
			colori=colori+c.getColore().toString()+"|";
		}
		String cartePolitica=colori+"|multicolor)";
		colori=colori+")";
				
		String regioni="(";
		for(Regione r: model.getRegioni()){
			regioni=regioni+r.toString()+"|";
		}
		regioni=regioni+")";
		
		String città="(";
		for(Città c: model.getCittà()){
			città=città+c.toString()+"|";
		}
		città=città+")";
		String indiceTessera="((\\d)*)";
		String atp="(1p) "+regioni+" "+cartePolitica+"{1,4}"+" ([1-2])";
		String cr="(2p) "+città+" "+cartePolitica;
		String ctp="(3p) "+indiceTessera+" "+città;
		String ec="(4p) "+regioni+" "+colori;
		String ct="(1v) "+regioni;
		String ecv="(2v) "+regioni+" "+colori;
		String ia="(3v)";
		String sap="(4v)";
		pattern= Pattern.compile("("+atp+"|"+cr+"|"+ctp+"|"+ec+"|"+ct+"|"+ecv+"|"+ia+"|"+sap+")");
		
	}
	
	@Override	
	public void input(Scanner scanner) {
		System.out.println("Inserisci numero giocatori");
		String input=scanner.nextLine();
		while(!isNumeric(input))
			System.out.println("valore non valido: inserisci numero giocatori");
		parser.instanziaGiocatori(input);
		
		
	}
	

	private boolean isNumeric(String input) {
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
	

}
