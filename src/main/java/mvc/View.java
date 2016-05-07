package mvc;

import java.util.Scanner;

import game.Partita;

public class View extends Observable implements Observer {
	
	private Partita partita;
	
	
	/**
	 * 
	 */
	public View(Partita partita) {
		this.partita=partita;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <C> void update(C change) {
		// TODO Auto-generated method stub
		
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

	public void scegliAzionePrincipale() {
		System.out.println("Elezione consigliere[1p]\n Costruzione Aiuto Re[2p]\n"
				+ "Costruzione Tessera Permesso[3p]\n Acquisto Tessera Permesso[4p]" );
		Scanner scanner = new Scanner(System.in);
		String messaggio = scanner.nextLine();
		if(!messaggio.equals("1p") && !messaggio.equals("2p") && !messaggio.equals("3p")
				&& !messaggio.equals("4p")){
			System.out.println("inserimento non valido");
			scegliAzionePrincipale();
		}
		
		this.notifyObservers(messaggio);
		}
			
}

	

