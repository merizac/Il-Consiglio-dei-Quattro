package client;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import gameDTO.gameDTO.BalconeDTO;
import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ColoreDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;
import utility.Utils;

public class AzioniClient {

	private String comando;

	/**
	 * this method let the player choose the consigliere which want to add int
	 * the balcony if the player insert a consigliere that not exists, he will
	 * insert again the consigliere
	 * 
	 * @return ConsigliereDTO choose from the player
	 */
	public ConsigliereDTO scegliConsigliere(ArrayList<ConsigliereDTO> consiglieri, Scanner stdIn) {
		System.out.println("scegli consigliere");
		System.out.println(consiglieri);
		comando = stdIn.nextLine();
		ConsigliereDTO consigliereScelto = ControlloParametriDTO.consiglieri(comando, consiglieri);
		while (consigliereScelto == null) {
			System.out.println("consigliere non esistente, inserire un valore valido");
			comando = stdIn.nextLine();
			consigliereScelto = ControlloParametriDTO.consiglieri(comando, consiglieri);
		}
		return consigliereScelto;
	}

	/**
	 * this method let the player choose the Regione if the player insert a
	 * regione that not exists, he will insert again the regione
	 * 
	 * @return RegioneDTO choose from the player
	 */
	public RegioneDTO scegliRegione(ArrayList<RegioneDTO> regioni, Scanner stdIn) {

		System.out.println("Scegli la regione");
		System.out.println(regioni);
		comando = stdIn.nextLine();
		RegioneDTO regioneScelta = ControlloParametriDTO.regioni(comando, regioni);
		while (regioneScelta == null) {
			System.out.println("la regione selezionata non è esistente! \nInserire di nuovo:");
			comando = stdIn.nextLine();
			regioneScelta = ControlloParametriDTO.regioni(comando, regioni);
		}
		return regioneScelta;
	}
	

	public BalconeDTO scegliBalcone(ArrayList<RegioneDTO> regioni, BalconeDTO balconeRe, Scanner stdIn) {
		BalconeDTO balconeScelto= null;
		boolean ok=false;
		System.out.println("Scegli il balcone");
		for(RegioneDTO regione: regioni){
			System.out.println("Balcone "+ regione.getNome() + ": " + regione.getBalcone()
								+ "[" + regione.getNome() + "]" );
		}
		System.out.println("Balcone re: " + balconeRe + "[Re]");
		
		while(!ok){
			comando = stdIn.nextLine();
			if(comando.equals("Mare")){
				ok=true;
				balconeScelto = regioni.get(0).getBalcone();
			}
			if(comando.equals("Collina")){
				ok=true;
				balconeScelto = regioni.get(1).getBalcone();
			}
			if(comando.equals("Montagna")){
				ok=true;
				balconeScelto = regioni.get(2).getBalcone();
			}
			if(comando.equals("Re")){
				ok=true;
				balconeScelto = balconeRe;
			}
			else {
				System.out.println("il balcone scelto non è esistente! \nInserire di nuovo!");
			}
		}
		return balconeScelto;
	}
	
	public ArrayList<CartaPoliticaDTO> scegliCarte(ArrayList<CartaPoliticaDTO> carteGiocatore, Scanner stdIn) {
		ArrayList<CartaPoliticaDTO> cartePolitica = new ArrayList<>();
		int numeroCarte = 3;
		while (numeroCarte != 0) {
			System.out.println("Scegli il colore delle carte politica nella tua mano");
			System.out.println(carteGiocatore);
			comando = stdIn.nextLine();
			CartaPoliticaDTO cartaScelta = ControlloParametriDTO.carteGiocatore(comando, carteGiocatore);
			while (cartaScelta == null) {
				System.out.println("la carta selezionanata non è esistente!\n Inserire di nuovo");
				comando = stdIn.nextLine();
				cartaScelta = ControlloParametriDTO.carteGiocatore(comando, carteGiocatore);
			}
			cartePolitica.add(cartaScelta);
			System.out.println("Vuoi aggiungere un'altra carta [Y/Other]");
			comando = stdIn.nextLine();
			if (comando.equals("Y")) {
				numeroCarte--;
				continue;
			} else
				break;
		}
		return cartePolitica;
	}

	/*public int scegliTesseraRegione(ArrayList<TesseraPermessoDTO> tessere, Scanner stdIn) {

		System.out.println("Seleziona tessera permesso[1/2]");
		for (TesseraPermessoDTO t : tessere)
			System.out.println(t);
		comando = stdIn.nextLine();
		while (!comando.equals("1") && !comando.equals("2")) {
			System.out.println("tessera selezionata non è esistente|\n Inserire di nuovo");
			comando = stdIn.nextLine();
		}

		return Integer.parseInt(comando) - 1;
	}*/
	
	public TesseraPermessoDTO scegliTesseraRegione(ArrayList<TesseraPermessoDTO> tessere, Scanner stdIn) {

		System.out.println("Seleziona tessera permesso[1/2]");
		for (TesseraPermessoDTO t : tessere)
			System.out.println(t);
		comando = stdIn.nextLine();
		while (!comando.equals("1") && !comando.equals("2")) {
			System.out.println("tessera selezionata non è esistente|\n Inserire di nuovo");
			comando = stdIn.nextLine();
		}

		return tessere.get((Integer.parseInt(comando) - 1));
	}

	public TesseraPermessoDTO scegliTesseraGiocatore(ArrayList<TesseraPermessoDTO> tessere, Scanner stdIn) {
		System.out.println("Seleziona l'indice di una tessera permesso non ancora usata");
		System.out.println(tessere);
		comando = stdIn.nextLine();
		TesseraPermessoDTO tesseraScelta = ControlloParametriDTO.tessereGiocatore(comando, tessere);
		while (tesseraScelta == null) {
			System.out.println("la tessera selezionanata non è esistente!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			tesseraScelta = ControlloParametriDTO.tessereGiocatore(comando, tessere);
		}
		return tesseraScelta;
	}

	public CittàDTO scegliCittà(Set<CittàDTO> città, ColoreDTO coloreGiocatore, Scanner stdIn) {
		System.out.println("Seleziona la città in cui costruire");
		System.out.println(città);
		comando = stdIn.nextLine();
		CittàDTO cittàScelta = ControlloParametriDTO.città(comando, città, coloreGiocatore);
		while (cittàScelta == null) {
			System.out.println("la città selezionata non è esistente o contiente già un emporio!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			cittàScelta = ControlloParametriDTO.città(comando, città, coloreGiocatore);
		}
		return cittàScelta;
	}

	public CartaPoliticaDTO scegliCarta(ArrayList<CartaPoliticaDTO> cartePolitica, Scanner stdIn) {
		System.out.println("Seleziona la carta politica");
		System.out.println(cartePolitica);
		comando = stdIn.nextLine();
		CartaPoliticaDTO cartaScelta = ControlloParametriDTO.carteGiocatore(comando, cartePolitica);
		while (cartaScelta == null) {
			System.out.println("la carta selezionanata non è esistente!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			cartaScelta = ControlloParametriDTO.carteGiocatore(comando, cartePolitica);
		}
		return cartaScelta;
	}

	public int scegliPrezzo(Scanner stdIn) {
		System.out.println("A quale prezzo?");
		comando = stdIn.nextLine();
		while (!Utils.isNumeric(comando)) {
			System.out.println("inserire un numero");
			comando = stdIn.nextLine();
		}
		return Integer.parseInt(comando);
	}


}
