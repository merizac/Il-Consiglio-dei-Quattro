package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import common.gameDTO.BalconeDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ColoreDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import utility.Utils;

public class AzioniClient {

	private String comando;

	/**
	 * this method let the player choose a counselor which want to add into
	 * the balcony if the player insert a counselor that not exists, he will
	 * insert again the counselor
	 * 
	 * @return ConsigliereDTO choose from the player
	 */
	public ConsigliereDTO scegliConsigliere(List<ConsigliereDTO> consiglieri, Scanner stdIn) {
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
	 * this method let the player choose the region if the player insert a
	 * region that not exists, he will insert again the region
	 * 
	 * @return RegioneDTO choose from the player
	 */
	public RegioneDTO scegliRegione(List<RegioneDTO> regioni, Scanner stdIn) {

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
	
	/**
	 * this method let the player chose the balcony where he want to elect a counselor
	 * @param regioni
	 * @param balconeRe
	 * @param stdIn
	 * @return the balcony DTO selected
	 */
	public BalconeDTO scegliBalcone(List<RegioneDTO> regioni, BalconeDTO balconeRe, Scanner stdIn) {
		BalconeDTO balconeScelto = null;
		boolean ok = false;
		System.out.println("Scegli il balcone");
		for (RegioneDTO regione : regioni) {
			System.out.println(
					"Balcone " + regione.getNome() + ": " + regione.getBalcone() + "[" + regione.getNome() + "]");
		}
		System.out.println("Balcone re: " + balconeRe + "[Re]");

		while (!ok) {
			comando = stdIn.nextLine();
			if (comando.equals("Mare")) {
				ok = true;
				balconeScelto = regioni.get(0).getBalcone();
			} else if (comando.equals("Collina")) {
				ok = true;
				balconeScelto = regioni.get(1).getBalcone();
			} else if (comando.equals("Montagna")) {
				ok = true;
				balconeScelto = regioni.get(2).getBalcone();
			} else if (comando.equals("Re")) {
				ok = true;
				balconeScelto = balconeRe;
			} else {
				System.out.println("il balcone scelto non è esistente! \nInserire di nuovo!");
			}
		}
		return balconeScelto;
	}
	/**
	 * this method let the player chose cards that hew want to use 
	 * @param carteGiocatore
	 * @param stdIn
	 * @return cards DTO selected
	 */
	public List<CartaPoliticaDTO> scegliCarte(List<CartaPoliticaDTO> carteGiocatore, Scanner stdIn) {
		List<CartaPoliticaDTO> cartePolitica = new ArrayList<>();
		int numeroCarte = 4;
		while (numeroCarte != 0) {
			numeroCarte--;
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
			if (numeroCarte != 0) {
				System.out.println("Vuoi aggiungere un'altra carta [Y/Other]");
				comando = stdIn.nextLine();
				if (comando.equals("Y")) {
					continue;
				} else
					break;
			}
		}
		return cartePolitica;
	}
	/**
	 * this method let the player to chose a permit tile of the region selected 
	 * @param tessere
	 * @param stdIn
	 * @return the permit tile DTO selected
	 */
	public TesseraPermessoDTO scegliTesseraRegione(List<TesseraPermessoDTO> tessere, Scanner stdIn) {

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
	/**
	 * this method let the player to chose a permit tile from his permit tiles not yet used
	 * @param list
	 * @param stdIn
	 * @return the permit tile DTO selected
	 */
	public TesseraPermessoDTO scegliTesseraGiocatore(List<TesseraPermessoDTO> list, Scanner stdIn) {
		System.out.println("Seleziona l'indice di una tessera permesso non ancora usata");
		System.out.println(list);
		comando = stdIn.nextLine();
		TesseraPermessoDTO tesseraScelta = ControlloParametriDTO.tessereGiocatore(comando, list);
		while (tesseraScelta == null) {
			System.out.println("la tessera selezionanata non è esistente!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			tesseraScelta = ControlloParametriDTO.tessereGiocatore(comando, list);
		}
		return tesseraScelta;
	}

	/**
	 * this method let the player chose a city where he want to build an emporium
	 * @param città
	 * @param coloreGiocatore
	 * @param stdIn
	 * @return the city DTO selected
	 */
	public CittàDTO scegliCittà(Set<? extends CittàDTO> città, ColoreDTO coloreGiocatore, Scanner stdIn) {
		System.out.println("Seleziona la città");
		System.out.println(città);
		comando = stdIn.nextLine();
		CittàDTO cittàScelta = ControlloParametriDTO.città(comando, città, coloreGiocatore);
		while (cittàScelta == null) {
			System.out.println("la città selezionata non è corretta!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			cittàScelta = ControlloParametriDTO.città(comando, città, coloreGiocatore);
		}
		return cittàScelta;
	}
	
	/**
	 * this method let the player to chose a politic card from the politic cards of his hand
	 * @param cartePolitica
	 * @param stdIn
	 * @return the politic card DTO
	 */
	public CartaPoliticaDTO scegliCarta(List<CartaPoliticaDTO> cartePolitica, Scanner stdIn) {
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
	/**
	 * this method let the player chose the price for an offer
	 * @param stdIn
	 * @return the price
	 */
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
