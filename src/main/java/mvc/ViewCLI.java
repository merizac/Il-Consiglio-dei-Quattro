package mvc;

import java.util.ArrayList;
import java.util.Scanner;

import game.Consigliere;
import game.Partita;
import game.Regione;

public class ViewCLI extends View {

	public ViewCLI(Partita partita) {
		super(partita);
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public <C> void update(C change) {
		
	}

	public String scegliAzione() {
		System.out.println("Azione principale o azione veloce?[1,2]");
		Scanner scanner = new Scanner(System.in);
		String messaggio=scanner.nextLine();
		
		if(!messaggio.equals("1") && !messaggio.equals("2")){
			System.out.println("inserimento non valido");
			messaggio=scegliAzione();
		}
		
		scanner.close();
		
		return messaggio;
	}
	
public String possibilitàAzioneVeloce(){
	System.out.println("Vuoi fare un'azione veloce?[Y/N]");
	Scanner scanner = new Scanner(System.in);
	String messaggio = scanner.nextLine();
	
	if(!messaggio.equals("Y") && !messaggio.equals("N")){
		System.out.println("inserimento non valido");
		messaggio = possibilitàAzioneVeloce();
	}
	scanner.close();
	return messaggio;
	
	}

	public void scegliAzionePrincipale() {
		System.out.println(" Elezione consigliere[1p]\n Costruzione Aiuto Re[2p]\n"
				+ "Costruzione Tessera Permesso[3p]\n Acquisto Tessera Permesso[4p]" );
		Scanner scanner = new Scanner(System.in);
		String messaggioP = scanner.nextLine();
		if(!messaggioP.equals("1p") && !messaggioP.equals("2p") && !messaggioP.equals("3p")
				&& !messaggioP.equals("4p")){
			System.out.println("inserimento non valido");
			scegliAzionePrincipale();
		}
		
		this.notifyObservers(messaggioP);
		scanner.close();
		}
	
	public void scegliAzioneVeloce(){
		System.out.println(" Ingaggiare aiutante[1v]\n Cambiare tessera permesso[2v]\n"
				+ " Elezione consigliere[3v]\n Seconda azione principale[4v]");
		Scanner scanner = new Scanner(System.in);
		String messaggioV = scanner.nextLine();
		if(!messaggioV.equals("1v") && !messaggioV.equals("2v") && !messaggioV.equals("3v")
				&& !messaggioV.equals("4v")){
			System.out.println("inserimento non valido");
			scegliAzioneVeloce();
		}
		
		this.notifyObservers(messaggioV);
		scanner.close();
	}

	public ArrayList<String> scegliCarte() {
		Scanner scanner= new Scanner(System.in);
		ArrayList<String> carte= new ArrayList<String>();
		System.out.println("Seleziona Carte politica[END to exit]");
	
		for(int i=0; i<4; i++){
			if(scanner.nextLine().equals("END"))
				break;
			else
				carte.add(scanner.nextLine());
		}
		scanner.close();
		return carte;
		
		
	}

	@Override
	public String scegliRegione() {
		Scanner scanner = new Scanner(System.in);
		String messaggio;
		while(true){
			System.out.println("Scegli regione");
			messaggio=scanner.nextLine();
			if(messaggio.equals("mare") || messaggio.equals("collina" ) || messaggio.equals("montagna"))
				break;
		}
		scanner.close();
		return messaggio;
	}
	
	@Override
	public String scegliConsigliere(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Scegli il colore del consigliere da inserire nel balcone");
		String messaggio = scanner.nextLine();
		scanner.close();
		return messaggio;
	}

	@Override
	public int scegliTesseraScoperta(Regione regione) {
		Scanner scanner = new Scanner(System.in);
		String indiceTessera;
		while(true){
			System.out.println("Scegli tessera scoperta");
			indiceTessera=scanner.nextLine();
			int check;
			try{
				check=Integer.parseInt(indiceTessera);
				if(check>0 || check<=regione.getTesserePermessoScoperte().size())
					break;
			}
			catch(NumberFormatException e){
				System.out.println("Hai inserito" + indiceTessera + ", ma non è un valore valido");
				continue;
			}
			
				
		}
		scanner.close();
		return Integer.parseInt(indiceTessera) ;
	}

	@Override
	public int erroreArrayList(String carta) {
		System.out.println("indice "+ carta + " inesistente, reinserirlo");
		String indiceTessera;
		Scanner scanner= new Scanner(System.in);
		indiceTessera=scanner.nextLine();
		scanner.close();
		return Integer.parseInt(indiceTessera);
	}
	
}